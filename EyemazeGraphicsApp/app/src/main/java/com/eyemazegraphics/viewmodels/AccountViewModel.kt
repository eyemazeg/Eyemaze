package com.eyemazegraphics.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyemazegraphics.database.AccountDao
import com.eyemazegraphics.database.TransactionDao
import com.eyemazegraphics.models.Account
import com.eyemazegraphics.models.Transaction
import com.eyemazegraphics.utils.NotificationHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class AccountViewModel(
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao,
    private val context: Context
) : ViewModel() {

    val allAccounts: Flow<List<Account>> = accountDao.getAllAccounts()

    fun addCashTransaction(accountId: Int, amount: Double, description: String) = viewModelScope.launch {
        // In a real app, you would also update the account balance
        val transaction = Transaction(
            accountId = accountId,
            type = "Credit",
            amount = amount,
            description = description,
            date = Date()
        )
        transactionDao.insert(transaction)

        // Trigger the notification
        NotificationHelper(context).showCashTransactionNotification("New cash transaction: $amount")
    }
}
