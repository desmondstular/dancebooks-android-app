package com.example.invoice.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DanceClass(
    @PrimaryKey(autoGenerate = true)
    val classId : Int = 0,
    val name : String,
    val lumpSumCost : Float,
    val biAnnualCost : Float,
    val monthlyCost : Float
)
