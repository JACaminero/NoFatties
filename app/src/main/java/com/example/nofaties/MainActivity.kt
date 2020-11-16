package com.example.nofaties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nofaties.adapters.RecordRecyclerAdapter
import com.example.nofaties.services.FireStoreRecordService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recView = findViewById<RecyclerView>(R.id.recyclerView2)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recView.layoutManager = layoutManager

        recView.addItemDecoration(
            DividerItemDecoration( recView.context, DividerItemDecoration.VERTICAL ))

        var records = FireStoreRecordService().getGoalRecords()
        val adapter = RecordRecyclerAdapter(records)
        recView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
//            R.id.action_add_record -> {
////                val intent = Intent(this, GoalRegistryFragment::class.java)
////                startActivity(intent)
////                true
//            }
            R.id.action_restart_goal -> {
                val intent = Intent(this, GoalRegistry::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}