package com.eyemazegraphics.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemId: Int,
    val quantity: Int,
    val totalPrice: Double,
    val saleDate: Date
)
