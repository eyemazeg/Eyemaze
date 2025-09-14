package com.eyemazegraphics.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eyemazegraphics.models.Item

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("DELETE FROM items WHERE id = :itemId")
    suspend fun delete(itemId: Int)

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): kotlinx.coroutines.flow.Flow<List<Item>>
}
