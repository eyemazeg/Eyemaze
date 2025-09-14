package com.eyemazegraphics.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eyemazegraphics.EyemazeApplication
import com.eyemazegraphics.adapters.SalesAdapter
import com.eyemazegraphics.databinding.ActivitySalesBinding
import com.eyemazegraphics.models.Item
import com.eyemazegraphics.viewmodels.SalesViewModel
import com.eyemazegraphics.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SalesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalesBinding
    private val viewModel: SalesViewModel by viewModels {
        ViewModelFactory(
            (application as EyemazeApplication).database.itemDao(),
            (application as EyemazeApplication).database.saleDao()
        )
    }
    private var selectedItem: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val salesAdapter = SalesAdapter()
        binding.salesRecyclerView.adapter = salesAdapter
        binding.salesRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.allSales.collect { sales ->
                salesAdapter.submitList(sales)
            }
        }

        lifecycleScope.launch {
            viewModel.allItems.collect { items ->
                val itemNames = items.map { it.name }
                val spinnerAdapter = ArrayAdapter(this@SalesActivity, android.R.layout.simple_spinner_item, itemNames)
                binding.itemSpinner.adapter = spinnerAdapter
                binding.itemSpinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                        selectedItem = items[position]
                    }
                    override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                        selectedItem = null
                    }
                }
            }
        }

        binding.recordSaleButton.setOnClickListener {
            recordSale()
        }
    }

    private fun recordSale() {
        val quantitySold = binding.quantitySoldEditText.text.toString().toIntOrNull()
        if (selectedItem != null && quantitySold != null && quantitySold > 0) {
            viewModel.recordSale(selectedItem!!, quantitySold)
            binding.quantitySoldEditText.text?.clear()
        } else {
            Toast.makeText(this, "Please select an item and enter a valid quantity", Toast.LENGTH_SHORT).show()
        }
    }
}
