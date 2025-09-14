package com.eyemazegraphics.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eyemazegraphics.EyemazeApplication
import com.eyemazegraphics.adapters.CustomerAdapter
import com.eyemazegraphics.databinding.ActivityCrsBinding
import com.eyemazegraphics.viewmodels.CustomerViewModel
import com.eyemazegraphics.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CRSActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrsBinding
    private val viewModel: CustomerViewModel by viewModels {
        ViewModelFactory(customerDao = (application as EyemazeApplication).database.customerDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val adapter = CustomerAdapter()
        binding.customerRecyclerView.adapter = adapter
        binding.customerRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.allCustomers.collect { customers ->
                adapter.submitList(customers)
            }
        }

        binding.fabAddCustomer.setOnClickListener {
            val intent = Intent(this, CustomerDetailActivity::class.java)
            startActivity(intent)
        }
    }
}
