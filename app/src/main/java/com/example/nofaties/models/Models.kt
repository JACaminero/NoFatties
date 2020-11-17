package com.example.nofaties.models

import java.util.*

data class Goal(var startDate: Date?, var startWeight: Float?,
                var goalWeight: Float?, var goalDate: Date?,
                var isCurrent: Boolean?, var userId: String?)

data class Record(var recordDate: com.google.firebase.Timestamp?, var recordWeight: Float?, var goalId: String?)

data class RecordShow(var recordDate: String?, var recordWeight: String?)