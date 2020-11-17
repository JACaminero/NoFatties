package com.example.nofaties.services

import com.example.nofaties.models.Goal
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class FireStoreGoalService: IFireStoreService<Goal> {

    private val db = IFireStoreService.db
    // docId es el mismo que el userId
    private val docId = FirebaseAuthService.currentUser?.uid.toString()

    override fun insert(item: Goal): Task<Void> {
        return db.collection("goals")
            .document(FirebaseAuthService.currentUser?.uid.toString())
            .set(item)
    }

    override fun delete(item: Goal) {

    }

    override fun update(item: Goal, document: String) {
        
    }

    override fun getAll(): Task<QuerySnapshot> {
        TODO("Not yet implemented")
    }

    fun getOriginalWeight(): Task<DocumentSnapshot> {
        return db.collection("goals")
            .document(docId).get()
    }

    fun getActualWeight(): Task<QuerySnapshot> {
        return db.collection("records")
            .whereEqualTo("goalId", docId)
            .get()
    }
}