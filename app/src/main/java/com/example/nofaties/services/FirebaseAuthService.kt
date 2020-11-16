package com.example.nofaties.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthService {

    fun signUp(email: String, password: String, name: String, lastname: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun signIn(username: String, password: String): Task<AuthResult> {
            return auth.signInWithEmailAndPassword(username, password)
    }

    companion object {
        private val auth: FirebaseAuth = Firebase.auth
        var currentUser = auth.currentUser
    }
}