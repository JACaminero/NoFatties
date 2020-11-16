package com.example.nofaties.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.nofaties.MainActivity
import com.example.nofaties.R
import com.example.nofaties.services.FirebaseAuthService

class LoginActivity : AppCompatActivity() {

    private lateinit var txtUsername: EditText
    private lateinit var txtPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtUsername = findViewById(R.id.txt_username_login)
        txtPass = findViewById(R.id.txt_password_login)


        findViewById<Button>(R.id.btn_signup_login).setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_login_true).setOnClickListener {
            var username: String =txtUsername.text.toString()
            var pass: String = txtPass.text.toString()
            if (username.isNotBlank() && pass.isNotBlank()) {
                val request = FirebaseAuthService().signIn(username, pass)

                request.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        FirebaseAuthService.currentUser?.displayName

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Datos incorrectos. Por favor reintente",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText( this, "Favol llenar todo lo dato", Toast.LENGTH_SHORT).show()
            }
        }
    }
}