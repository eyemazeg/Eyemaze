package com.eyemazegraphics.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyemazegraphics.database.CustomerDao
import com.eyemazegraphics.models.Customer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CustomerViewModel(private val customerDao: CustomerDao) : ViewModel() {

    val allCustomers: Flow<List<Customer>> = customerDao.getAllCustomers()

    fun insert(customer: Customer) = viewModelScope.launch {
        customerDao.insert(customer)
    }
}
