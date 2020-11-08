package com.example.nofaties.services

import com.example.nofaties.models.Record
import java.util.*

class FireStoreRecordService: IFireStoreService<Record> {

    val db = IFireStoreService.db

    override fun getAll(): List<Record> {

        return listOf( Record( Date(),5f, 4f, Date(), "" ) )
    }

    override fun insert(item: Record) {

    }

    override fun delete(item: Record) {

    }

    override fun update(item: Record, document: String) {

    }

    companion object {
        private const val DOCUMENT = ""
    }
}