package com.example.nofaties.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.nofaties.MainActivity
import com.example.nofaties.R
import com.example.nofaties.services.FirebaseAuthService

class SignUpActivity : AppCompatActivity() {

    private lateinit var fbs: FirebaseAuthService
    private lateinit var txtUser: TextView
    private lateinit var txtPassword: TextView
    private lateinit var txtName: TextView
    private lateinit var txtLast: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fbs = FirebaseAuthService()
        txtUser = findViewById(R.id.txt_username)
        txtPassword = findViewById(R.id.txt_password)
        txtLast = findViewById(R.id.txt_lastname)
        txtName = findViewById(R.id.txt_name)

        findViewById<Button>(R.id.btn_signup).setOnClickListener {
            val isSuccessful = fbs.signUp( txtUser.text.toString(), txtPassword.text.toString(),
                    txtName.text.toString(), txtLast.text.toString(), this )

            if (isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}