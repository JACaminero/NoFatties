package com.example.nofaties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nofaties.adapters.RecordRecyclerAdapter
import com.example.nofaties.models.Goal
import com.example.nofaties.models.Record
import com.example.nofaties.models.RecordShow
import com.example.nofaties.services.FireStoreGoalService
import com.example.nofaties.services.FireStoreRecordService
import com.google.firebase.Timestamp
import com.google.firestore.v1.FirestoreGrpc
import java.lang.Float.parseFloat
import java.text.DateFormat.SHORT
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private var records: MutableList<RecordShow> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FireStoreRecordService().getAll()
            .addOnSuccessListener { snapshot ->
                for(doc in snapshot) {
                    var map = doc.data
                    var weight = map.get("recordWeight").toString()
                    var date = map.get("recordDate") as Timestamp
                    var dateFormated = getDateInstance(SHORT).format(date.toDate()).toString()
                    records?.add( RecordShow( dateFormated, weight ) )
                }
                loadUserData()
            }
    }

    private fun loadUserData() {
        val recView = findViewById<RecyclerView>(R.id.recyclerView2)
        recView.addItemDecoration(
                DividerItemDecoration( recView.context, DividerItemDecoration.VERTICAL ))
        val layoutManager = LinearLayoutManager(this)
        recView.layoutManager = layoutManager

        val adapter = RecordRecyclerAdapter(records)
        recView.adapter = adapter

        var originalWeight: Float = 0f
        var goalWeight: Float = 0f
        var monthProgress: Float = 0f
        var restante: Float = 0f
        var weekProgress: Float = 0f
        var progressDay: Float = 0f

        FireStoreGoalService().getOriginalWeight()
            .addOnCompleteListener {
            if (it.isSuccessful) {
                val map = it.result?.data
                originalWeight = parseFloat( map?.get("startWeight").toString() )
                findViewById<EditText>(R.id.et_original).setText(originalWeight.toString())

                goalWeight = parseFloat( map?.get("goalWeight").toString() )
                findViewById<EditText>(R.id.et_objetivo).setText(goalWeight.toString())
            }
        }

        FireStoreRecordService().getMonthlyProgress()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (doc in it.result?.documents!!) {
                        var progress = originalWeight - doc.data?.get("recordWeight").toString().toFloat()
                        monthProgress += progress
                    }
                    findViewById<EditText>(R.id.et_bottom_dock).setText(monthProgress.toString())
                }
            }

        FireStoreRecordService().getWeeklyProgress()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (doc in it.result?.documents!!) {
                        var progress = originalWeight - doc.data?.get("recordWeight").toString().toFloat()
                        weekProgress += progress
                    }
                    findViewById<EditText>(R.id.et_bottom_right).setText(weekProgress.toString())
                }
        }

        FireStoreRecordService().getTotal().addOnCompleteListener {
            if (it.isSuccessful) {
                for (doc in it.result?.documents!!) {
                    progressDay += doc.data?.get("recordWeight").toString().toFloat()
                }
                findViewById<EditText>(R.id.et_bottom_left).setText(progressDay.toString())
            }
        }

        FireStoreRecordService().restante().addOnCompleteListener {
            if (it.isSuccessful) {
                for(doc in it.result!!) {
                    restante = doc.data["recordWeight"].toString().toFloat()
                }
            }
            findViewById<EditText>(R.id.et_restante).setText(restante.toString())
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_record -> {
                val intent = Intent(this, RecordRegistry::class.java)
                startActivity(intent)
                true
            }
            R.id.action_restart_goal -> {
                val intent = Intent(this, GoalRegistry::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}