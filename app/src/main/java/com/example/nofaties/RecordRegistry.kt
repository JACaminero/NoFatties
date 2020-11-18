package com.example.nofaties

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.example.nofaties.models.Record
import com.example.nofaties.services.FireStoreGoalService
import com.example.nofaties.services.FireStoreRecordService
import com.example.nofaties.services.FirebaseAuthService
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class RecordRegistry : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_registry)

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            addRecord()
        }
    }

    fun showTimePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }


    private fun addRecord() {
        var peso =  findViewById<TextView>(R.id.et_peso_record).text.toString()
        var date =  findViewById<TextView>(R.id.txt_fecha_record).text.toString()

        val tz = ZoneId.systemDefault()
        val localDate = LocalDateTime.parse("${date} 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
        val seconds = localDate.atZone(tz).toEpochSecond()
        val nanos = localDate.nano
        val timestamp = com.google.firebase.Timestamp(seconds, nanos)

        var record = Record( timestamp, peso.toFloat(), FirebaseAuthService.currentUser?.uid )

        FireStoreRecordService().insertTrue(record)
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(baseContext, "Exito!!!.",
                    Toast.LENGTH_SHORT).show()
                startActivity( Intent(this, MainActivity::class.java) )
            } else {

            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val txtDate = findViewById<TextView>(R.id.txt_fecha_record)
        if (day <= 9) {
            txtDate.text = "0$day-$month-$year"
        } else{
            txtDate.text = "$day-$month-$year"
        }
    }
}