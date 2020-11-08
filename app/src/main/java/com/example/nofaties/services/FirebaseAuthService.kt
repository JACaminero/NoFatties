package com.example.nofaties.services

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class FirebaseAuthService {

    fun signUp(email: String, password: String, name: String, lastname: String, activity: AppCompatActivity): Boolean {
        var isSuccessful = false
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "Exito")
                    Toast.makeText(activity.baseContext, "Exito.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    user?.updateProfile( userProfileChangeRequest {
                        displayName = "$name $lastname"
                    })
                    isSuccessful = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "Mal :(", task.exception)
                    Toast.makeText(activity.baseContext, "Denegado.",
                        Toast.LENGTH_SHORT).show()
                    isSuccessful = false
                }
            }
        return isSuccessful
    }

    fun signIn(username: String, password: String, activity: AppCompatActivity) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(activity.baseContext, "DENIED",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }

    companion object {
        private val auth: FirebaseAuth = Firebase.auth
        private const val TAG = "Account"
        var currentUser = auth.currentUser
    }
}