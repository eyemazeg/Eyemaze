package com.eyemazegraphics.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyemazegraphics.database.ItemDao
import com.eyemazegraphics.database.SaleDao
import com.eyemazegraphics.models.Item
import com.eyemazegraphics.models.Sale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class SalesViewModel(
    private val saleDao: SaleDao,
    private val itemDao: ItemDao
) : ViewModel() {

    val allSales: Flow<List<Sale>> = saleDao.getAllSales()
    val allItems: Flow<List<Item>> = itemDao.getAllItems()

    fun recordSale(item: Item, quantitySold: Int) = viewModelScope.launch {
        if (item.quantity >= quantitySold) {
            // Create a new sale record
            val sale = Sale(
                itemId = item.id,
                quantity = quantitySold,
                totalPrice = item.price * quantitySold,
                saleDate = Date()
            )
            saleDao.insert(sale)

            // Update the item's stock
            val updatedItem = item.copy(quantity = item.quantity - quantitySold)
            itemDao.update(updatedItem)
        }
    }
}
