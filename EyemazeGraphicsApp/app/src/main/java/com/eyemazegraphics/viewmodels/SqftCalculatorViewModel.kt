package com.eyemazegraphics.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyemazegraphics.database.CalculationDao
import com.eyemazegraphics.models.Calculation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class SqftCalculatorViewModel(private val calculationDao: CalculationDao) : ViewModel() {

    val allCalculations: Flow<List<Calculation>> = calculationDao.getAllCalculations()

    fun saveCalculation(
        length: Double,
        width: Double,
        pricePerSqft: Double,
        printingCharges: Double
    ) = viewModelScope.launch {
        val sqft = length * width
        val cost = sqft * pricePerSqft
        val totalCost = cost + printingCharges
        val calculation = Calculation(
            length = length,
            width = width,
            sqft = sqft,
            cost = cost,
            printingCharges = printingCharges,
            totalCost = totalCost,
            date = Date()
        )
        calculationDao.insert(calculation)
    }
}
