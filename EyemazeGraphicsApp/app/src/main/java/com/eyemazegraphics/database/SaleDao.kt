package com.eyemazegraphics.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eyemazegraphics.models.Sale
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {
    @Insert
    suspend fun insert(sale: Sale)

    @Query("SELECT * FROM sales ORDER BY saleDate DESC")
    fun getAllSales(): Flow<List<Sale>>
}
