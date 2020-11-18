package com.example.nofaties.services

import android.util.Log
import com.example.nofaties.models.Record
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class FireStoreRecordService: IFireStoreService<Record> {

    private val db = IFireStoreService.db

    override fun insert(item: Record): Task<Void> {
        return db.collection("goals")
            .document(FirebaseAuthService.currentUser?.uid.toString())
            .set(item)
    }

    fun insertTrue(item: Record): Task<DocumentReference> {
        return db.collection("records").add(item)
    }

    fun getTotal(): Task<QuerySnapshot> {
        return db.collection("records").whereEqualTo("goalId", FirebaseAuthService.currentUser?.uid)
            .get()
    }
    fun getWeeklyProgress(): Task<QuerySnapshot> {
        return db.collection("records").whereEqualTo("goalId", FirebaseAuthService.currentUser?.uid)
                .orderBy("recordDate")
                .whereLessThanOrEqualTo("recordDate", Timestamp.now())
                .endAt(Timestamp( Date.from( LocalDateTime.now().minusDays(7).atZone(ZoneId.systemDefault()).toInstant() )))
                .get()
    }

    fun getMonthlyProgress(): Task<QuerySnapshot> {
        return db.collection("records").whereEqualTo("goalId", FirebaseAuthService.currentUser?.uid)
                .orderBy("recordDate")
                .whereLessThanOrEqualTo("recordDate", Timestamp.now())
                .endAt(Timestamp( Date.from( LocalDateTime.now().minusDays(30).atZone(ZoneId.systemDefault()).toInstant() )))
                .get()
    }

    override fun delete(item: Record) {

    }

    override fun update(item: Record, document: String) {

    }


    fun restante(): Task<QuerySnapshot> {
        return db.collection("records").whereEqualTo("goalId", FirebaseAuthService.currentUser?.uid)
                .orderBy("recordDate", Query.Direction.ASCENDING)
                .limitToLast(1)
                .get()
    }
    override fun getAll(): Task<QuerySnapshot> {
        return db.collection("records").orderBy("recordDate")
            .whereEqualTo("goalId", FirebaseAuthService.currentUser?.uid)
            .get()
    }
}