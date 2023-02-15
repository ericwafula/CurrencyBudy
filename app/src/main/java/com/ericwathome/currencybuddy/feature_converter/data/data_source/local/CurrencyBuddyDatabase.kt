package com.ericwathome.currencybuddy.feature_converter.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericwathome.currencybuddy.common.Constants
import com.ericwathome.currencybuddy.feature_converter.domain.model.ExchangeRate

@Database(
    entities = [ExchangeRate::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
abstract class CurrencyBuddyDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}