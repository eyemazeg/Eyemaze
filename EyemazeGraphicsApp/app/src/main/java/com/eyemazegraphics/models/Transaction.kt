package com.eyemazegraphics.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountId: Int,
    val type: String, // "Credit", "Debit"
    val amount: Double,
    val description: String,
    val date: Date
)
