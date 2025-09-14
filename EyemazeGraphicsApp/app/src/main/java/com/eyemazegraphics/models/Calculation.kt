package com.eyemazegraphics.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "calculations")
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val length: Double,
    val width: Double,
    val sqft: Double,
    val cost: Double,
    val printingCharges: Double,
    val totalCost: Double,
    val date: Date
)
