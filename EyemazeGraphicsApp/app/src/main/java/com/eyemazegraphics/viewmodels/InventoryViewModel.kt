package com.eyemazegraphics.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyemazegraphics.database.ItemDao
import com.eyemazegraphics.models.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    val allItems: Flow<List<Item>> = itemDao.getAllItems()

    fun insert(item: Item) = viewModelScope.launch {
        itemDao.insert(item)
    }

    fun update(item: Item) = viewModelScope.launch {
        itemDao.update(item)
    }

    fun delete(itemId: Int) = viewModelScope.launch {
        itemDao.delete(itemId)
    }
}
