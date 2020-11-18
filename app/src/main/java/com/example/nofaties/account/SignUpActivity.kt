package com.example.nofaties.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.nofaties.GoalRegistry
import com.example.nofaties.MainActivity
import com.example.nofaties.R
import com.example.nofaties.services.FirebaseAuthService
import com.google.firebase.auth.ktx.userProfileChangeRequest

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
        txtUser = findViewById(R.id.txt_username_signup)
        txtPassword = findViewById(R.id.txt_password)
        txtLast = findViewById(R.id.txt_lastname)
        txtName = findViewById(R.id.txt_name)

        findViewById<Button>(R.id.btn_signup_login).setOnClickListener {
            var name= txtName.text.toString()
            var lastname = txtLast.text.toString()
            val result = fbs.signUp( txtUser.text.toString(), txtPassword.text.toString(), name, lastname)

            result.addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(baseContext, "Exito.",
                            Toast.LENGTH_SHORT).show()
                    val user = FirebaseAuthService.currentUser

                    user?.updateProfile(userProfileChangeRequest {
                        displayName = "$name $lastname"
                    })
                    val intent = Intent(this, GoalRegistry::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Denegado.",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}