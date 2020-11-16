package com.example.nofaties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.nofaties.models.Goal
import com.example.nofaties.services.FireStoreGoalService
import com.example.nofaties.services.FirebaseAuthService
import java.time.LocalDate
import java.util.*

class GoalRegistry : AppCompatActivity() {
    private var hola: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_registry)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        hola = findViewById(R.id.txt_hola)
        hola?.text = "Hola, ${FirebaseAuthService.currentUser?.displayName}"

        val goal = findViewById<EditText>(R.id.txt_meta)
        val pesoActual = findViewById<EditText>(R.id.txt_peso_actual)

        findViewById<Button>(R.id.btn_register_goal)?.setOnClickListener {
            FireStoreGoalService().insert(
                Goal( Calendar.getInstance().time,  pesoActual?.text.toString().toFloat(),
                    goal?.text.toString().toFloat(), null,
                    true, FirebaseAuthService.currentUser?.uid as String ))
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        successCallback()
                    }
                }
        }
    }

    private fun successCallback() {
        Toast.makeText(baseContext, "Exito!!!.",
            Toast.LENGTH_SHORT).show()
        startActivity(
            Intent(this, MainActivity::class.java )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
