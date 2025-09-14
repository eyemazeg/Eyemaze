package com.eyemazegraphics.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eyemazegraphics.databinding.CalculationRowBinding
import com.eyemazegraphics.models.Calculation
import java.text.SimpleDateFormat
import java.util.*

class CalculationAdapter : ListAdapter<Calculation, CalculationAdapter.CalculationViewHolder>(CalculationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculationViewHolder {
        val binding = CalculationRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalculationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalculationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CalculationViewHolder(private val binding: CalculationRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(calculation: Calculation) {
            binding.calculationInfo.text = "L: ${calculation.length}, W: ${calculation.width}, Total: ${calculation.totalCost}"
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            binding.calculationDate.text = sdf.format(calculation.date)
        }
    }
}

class CalculationDiffCallback : DiffUtil.ItemCallback<Calculation>() {
    override fun areItemsTheSame(oldItem: Calculation, newItem: Calculation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Calculation, newItem: Calculation): Boolean {
        return oldItem == newItem
    }
}
