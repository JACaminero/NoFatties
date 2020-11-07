package com.example.nofaties.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.nofaties.R
import com.example.nofaties.services.FirebaseAuthService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class SignUpActivity : AppCompatActivity() {

    private lateinit var fbs: FirebaseAuthService
    private lateinit var txtUser: TextView
    private lateinit var txtPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fbs = FirebaseAuthService()
        txtUser = findViewById(R.id.txt_username)
        txtPassword = findViewById(R.id.txt_password)

        findViewById<Button>(R.id.btn_signup).setOnClickListener {

            fbs.signUp( txtUser.text.toString(), txtPassword.text.toString(), this )

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}