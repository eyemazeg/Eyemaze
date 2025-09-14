package com.eyemazegraphics.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eyemazegraphics.models.Calculation
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculationDao {
    @Insert
    suspend fun insert(calculation: Calculation)

    @Query("SELECT * FROM calculations ORDER BY date DESC")
    fun getAllCalculations(): Flow<List<Calculation>>
}
