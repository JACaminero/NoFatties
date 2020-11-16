package com.example.nofaties.services

import com.example.nofaties.models.Goal
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import java.util.*

class FireStoreGoalService: IFireStoreService<Goal> {
    private val db = IFireStoreService.db
    override fun getAll(): List<Goal> {

        return listOf( Goal( Date(),5f, 4f, Date(), false, "" ) )
    }

    override fun insert(item: Goal): Task<DocumentReference> {
        return db.collection("goal").add(item)
    }

    fun getCurrentGoal() {

    }

    override fun delete(item: Goal) {

    }

    override fun update(item: Goal, document: String) {

    }
}