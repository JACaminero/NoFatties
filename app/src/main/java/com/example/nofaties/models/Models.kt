    package com.example.nofaties.models

import java.util.*

data class Goal(var startDate: Date, var startWeight: Float,
                var goalWeight: Float, var goalDate: Date?,
                var isCurrent: Boolean, val userId: String) {
}

data class Record(var recordDate: Date, var recordWeight: Float){

}