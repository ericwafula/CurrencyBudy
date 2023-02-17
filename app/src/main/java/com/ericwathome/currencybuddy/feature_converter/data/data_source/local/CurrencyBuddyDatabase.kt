package com.ericwathome.currencybuddy.feature_converter.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ericwathome.currencybuddy.common.AppConstants
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrencyInfo
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrentRate


@Database(
    entities = [CurrencyInfo::class, CurrentRate::class],
    version = AppConstants.DATABASE_VERSION,
    exportSchema = false
)
abstract class CurrencyBuddyDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}