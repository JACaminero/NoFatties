package com.example.nofaties.services

import com.google.firebase.firestore.FirebaseFirestore

interface IFireStoreService<T>  {
    fun insert(item: T)
    fun delete(item: T)
    fun update(item: T, document: String)
    fun getAll() : List<T>

    companion object {
        val db = FirebaseFirestore.getInstance()
    }
}