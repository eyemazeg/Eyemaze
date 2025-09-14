package com.eyemazegraphics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eyemazegraphics.databinding.SaleRowBinding
import com.eyemazegraphics.models.Sale
import java.text.SimpleDateFormat
import java.util.*

class SalesAdapter : ListAdapter<Sale, SalesAdapter.SaleViewHolder>(SaleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        val binding = SaleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SaleViewHolder(private val binding: SaleRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sale: Sale) {
            binding.saleInfo.text = "Sale ID: ${sale.id}, Item ID: ${sale.itemId}, Quantity: ${sale.quantity}"
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            binding.saleDate.text = sdf.format(sale.saleDate)
        }
    }
}

class SaleDiffCallback : DiffUtil.ItemCallback<Sale>() {
    override fun areItemsTheSame(oldItem: Sale, newItem: Sale): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sale, newItem: Sale): Boolean {
        return oldItem == newItem
    }
}
