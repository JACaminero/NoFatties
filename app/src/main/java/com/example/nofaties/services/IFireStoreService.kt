package com.example.nofaties.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

interface IFireStoreService<T>  {
    fun insert(item: T): Task<DocumentReference>
    fun delete(item: T)
    fun update(item: T, document: String)
    fun getAll() : List<T>

    companion object {
        val db = FirebaseFirestore.getInstance()
    }
}