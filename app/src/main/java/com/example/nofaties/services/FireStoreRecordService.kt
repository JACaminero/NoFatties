package com.example.nofaties.services

import com.example.nofaties.models.Record
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import java.util.*

class FireStoreRecordService: IFireStoreService<Record> {

    private val db = IFireStoreService.db

    override fun insert(item: Record): Task<DocumentReference>  {
        return db.collection("goals").document(/*documentId*/)
            .collection("records").add(item)
    }

    override fun delete(item: Record) {

    }

    override fun update(item: Record, document: String) {

    }

    fun getGoalRecords(): List<Record> {
        return emptyList()
    }

    override fun getAll(): List<Record> {

        return listOf( Record( Date(),5f ) )
    }

    companion object {
        private const val DOCUMENT = ""
    }
}