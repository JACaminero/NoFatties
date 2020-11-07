package com.example.nofaties.services

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nofaties.account.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthService {

    fun signUp(email: String, password: String, activity: AppCompatActivity) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "Exito")
                    Toast.makeText(activity.baseContext, "Exito.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "Mal :(", task.exception)
                    Toast.makeText(activity.baseContext, "Denegado.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    companion object {

        private val auth: FirebaseAuth = Firebase.auth
        private const val TAG = "Account"

    }
}