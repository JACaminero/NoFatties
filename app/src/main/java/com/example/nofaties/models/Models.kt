    package com.example.nofaties.models

import java.util.*

//data class User(var name: String, var lastname: String, val userId: String) {
//}

data class Record(var startDate: Date, var startWeight: Float,
                  var goalWeight: Float, var goalDate: Date, var isCurrent: Boolean, val userId: String) {
}
