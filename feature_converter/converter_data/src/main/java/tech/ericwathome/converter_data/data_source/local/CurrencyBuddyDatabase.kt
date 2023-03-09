package tech.ericwathome.converter_data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.ericwathome.converter_data.model.CurrencyInfo
import tech.ericwathome.converter_data.model.CurrentRate
import tech.ericwathome.converter_data.util.Constants


@Database(
    entities = [CurrencyInfo::class, CurrentRate::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
abstract class CurrencyBuddyDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}