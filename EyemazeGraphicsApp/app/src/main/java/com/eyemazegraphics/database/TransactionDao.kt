package com.eyemazegraphics.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eyemazegraphics.models.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE accountId = :accountId ORDER BY date DESC")
    fun getAllTransactionsForAccount(accountId: Int): Flow<List<Transaction>>
}
