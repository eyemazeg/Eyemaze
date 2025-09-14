package com.eyemazegraphics.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.eyemazegraphics.EyemazeApplication
import com.eyemazegraphics.databinding.ActivityCustomerDetailBinding
import com.eyemazegraphics.models.Customer
import com.eyemazegraphics.viewmodels.CustomerViewModel
import com.eyemazegraphics.viewmodels.ViewModelFactory

class CustomerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerDetailBinding
    private val viewModel: CustomerViewModel by viewModels {
        ViewModelFactory(customerDao = (application as EyemazeApplication).database.customerDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveCustomerButton.setOnClickListener {
            saveCustomer()
        }
    }

    private fun saveCustomer() {
        val name = binding.customerNameEditText.text.toString()
        val phone = binding.customerPhoneEditText.text.toString()
        val address = binding.customerAddressEditText.text.toString()

        if (name.isNotBlank()) {
            val customer = Customer(name = name, phone = phone, address = address)
            viewModel.insert(customer)
            finish()
        }
    }
}
