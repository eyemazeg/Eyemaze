package com.eyemazegraphics.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eyemazegraphics.EyemazeApplication
import com.eyemazegraphics.adapters.CalculationAdapter
import com.eyemazegraphics.databinding.ActivitySqftCalculatorBinding
import com.eyemazegraphics.viewmodels.SqftCalculatorViewModel
import com.eyemazegraphics.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.NumberFormat

class SqftCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySqftCalculatorBinding
    private val viewModel: SqftCalculatorViewModel by viewModels {
        ViewModelFactory(calculationDao = (application as EyemazeApplication).database.calculationDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqftCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CalculationAdapter()
        binding.calculationHistoryRecyclerView.adapter = adapter
        binding.calculationHistoryRecyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.allCalculations.collect { calculations ->
                adapter.submitList(calculations)
            }
        }

        binding.lengthEditText.doAfterTextChanged { calculate() }
        binding.widthEditText.doAfterTextChanged { calculate() }
        binding.pricePerSqftEditText.doAfterTextChanged { calculate() }
        binding.printingChargesEditText.doAfterTextChanged { calculate() }

        binding.saveCalculationButton.setOnClickListener {
            saveCalculation()
        }
    }

    private fun calculate() {
        val length = binding.lengthEditText.text.toString().toDoubleOrNull() ?: 0.0
        val width = binding.widthEditText.text.toString().toDoubleOrNull() ?: 0.0
        val pricePerSqft = binding.pricePerSqftEditText.text.toString().toDoubleOrNull() ?: 0.0
        val printingCharges = binding.printingChargesEditText.text.toString().toDoubleOrNull() ?: 0.0

        val sqft = length * width
        val cost = sqft * pricePerSqft
        val totalCost = cost + printingCharges

        binding.sqftResultText.text = "Sq.ft: ${formatDouble(sqft)}"
        binding.totalCostResultText.text = "Total Cost: ${formatDouble(totalCost)}"
    }

    private fun saveCalculation() {
        val length = binding.lengthEditText.text.toString().toDoubleOrNull() ?: 0.0
        val width = binding.widthEditText.text.toString().toDoubleOrNull() ?: 0.0
        val pricePerSqft = binding.pricePerSqftEditText.text.toString().toDoubleOrNull() ?: 0.0
        val printingCharges = binding.printingChargesEditText.text.toString().toDoubleOrNull() ?: 0.0

        if (length > 0 && width > 0 && pricePerSqft > 0) {
            viewModel.saveCalculation(length, width, pricePerSqft, printingCharges)
        }
    }

    private fun formatDouble(value: Double): String {
        val format = NumberFormat.getInstance()
        format.maximumFractionDigits = 2
        return format.format(value)
    }
}
