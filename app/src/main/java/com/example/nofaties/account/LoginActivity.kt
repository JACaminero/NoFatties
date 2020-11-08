package com.example.nofaties.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.nofaties.MainActivity
import com.example.nofaties.R
import com.example.nofaties.services.FirebaseAuthService

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.btn_login_true).setOnClickListener {
            FirebaseAuthService().signIn("","", this@LoginActivity)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}