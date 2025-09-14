package com.eyemazegraphics.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eyemazegraphics.models.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert
    suspend fun insert(customer: Customer)

    @Query("SELECT * FROM customers ORDER BY name ASC")
    fun getAllCustomers(): Flow<List<Customer>>
}
