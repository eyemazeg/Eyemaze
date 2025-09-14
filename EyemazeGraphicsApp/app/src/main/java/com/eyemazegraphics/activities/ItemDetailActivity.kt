package com.eyemazegraphics.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.eyemazegraphics.EyemazeApplication
import com.eyemazegraphics.databinding.ActivityItemDetailBinding
import com.eyemazegraphics.models.Item
import com.eyemazegraphics.viewmodels.InventoryViewModel
import com.eyemazegraphics.viewmodels.ViewModelFactory

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailBinding
    private val viewModel: InventoryViewModel by viewModels {
        ViewModelFactory((application as EyemazeApplication).database.itemDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveItemButton.setOnClickListener {
            saveItem()
        }
    }

    private fun saveItem() {
        val name = binding.itemNameEditText.text.toString()
        val category = binding.itemCategoryEditText.text.toString()
        val quantity = binding.itemQuantityEditText.text.toString().toIntOrNull() ?: 0
        val price = binding.itemPriceEditText.text.toString().toDoubleOrNull() ?: 0.0

        if (name.isNotBlank() && category.isNotBlank()) {
            val item = Item(name = name, category = category, quantity = quantity, price = price)
            viewModel.insert(item)
            finish()
        }
    }
}
