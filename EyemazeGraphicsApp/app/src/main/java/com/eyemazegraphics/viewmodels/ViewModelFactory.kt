package com.eyemazegraphics.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eyemazegraphics.database.*

class ViewModelFactory(
    private val context: Context,
    private val itemDao: ItemDao? = null,
    private val saleDao: SaleDao? = null,
    private val customerDao: CustomerDao? = null,
    private val accountDao: AccountDao? = null,
    private val transactionDao: TransactionDao? = null,
    private val calculationDao: CalculationDao? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao!!) as T
        }
        if (modelClass.isAssignableFrom(SalesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SalesViewModel(saleDao!!, itemDao!!) as T
        }
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CustomerViewModel(customerDao!!) as T
        }
        if (modelClass.isAssignableFrom(SqftCalculatorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SqftCalculatorViewModel(calculationDao!!) as T
        }
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountViewModel(accountDao!!, transactionDao!!, context) as T
        }
        // Add other ViewModels here
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
