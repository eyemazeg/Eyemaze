package com.eyemazegraphics.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eyemazegraphics.models.*
import com.eyemazegraphics.utils.DateConverter

@Database(entities = [Item::class, Sale::class, Customer::class, Account::class, Transaction::class, Calculation::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun saleDao(): SaleDao
    abstract fun customerDao(): CustomerDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun calculationDao(): CalculationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "eyemaze_database"
                )
                .fallbackToDestructiveMigration() // Not ideal for production, but fine for now
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
