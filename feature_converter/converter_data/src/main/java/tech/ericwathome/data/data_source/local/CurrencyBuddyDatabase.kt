package tech.ericwathome.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import tech.ericwathome.data.model.CurrencyInfo
import tech.ericwathome.data.model.CurrentRate
import tech.ericwathome.data.util.Constants


@Database(
    entities = [CurrencyInfo::class, CurrentRate::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
abstract class CurrencyBuddyDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}