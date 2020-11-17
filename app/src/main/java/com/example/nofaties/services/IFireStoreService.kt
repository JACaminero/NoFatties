package com.example.nofaties.services

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

interface IFireStoreService<T>  {
    fun insert(item: T): Task<Void>
    fun delete(item: T)
    fun update(item: T, document: String)
    fun getAll(): Task<QuerySnapshot>

    companion object {
        val db = FirebaseFirestore.getInstance()
    }
}