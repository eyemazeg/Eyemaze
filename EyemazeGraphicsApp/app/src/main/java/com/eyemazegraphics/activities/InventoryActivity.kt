package com.eyemazegraphics.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eyemazegraphics.EyemazeApplication
import com.eyemazegraphics.adapters.ItemAdapter
import com.eyemazegraphics.databinding.ActivityInventoryBinding
import com.eyemazegraphics.viewmodels.InventoryViewModel
import com.eyemazegraphics.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class InventoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryBinding
    private val viewModel: InventoryViewModel by viewModels {
        ViewModelFactory((application as EyemazeApplication).database.itemDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val adapter = ItemAdapter()
        binding.inventoryRecyclerView.adapter = adapter
        binding.inventoryRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.allItems.collect { items ->
                adapter.submitList(items)
            }
        }

        binding.fabAddItem.setOnClickListener {
            val intent = Intent(this, ItemDetailActivity::class.java)
            startActivity(intent)
        }
    }
}
