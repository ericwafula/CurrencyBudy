package tech.ericwathome.converter_data.data_source.local

import androidx.room.*
import tech.ericwathome.converter_data.model.CurrencyInfo
import tech.ericwathome.converter_data.model.CurrentRate
import tech.ericwathome.converter_data.model.relations.CurrencyInfoWithCurrentRates

@Dao
interface ExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyInfo(currencyInfo: CurrencyInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyRate(currentRate: CurrentRate)

    @Delete
    suspend fun deleteCurrencyInfo(currencyInfo: CurrencyInfo)

    @Delete
    suspend fun deleteCurrencyRate(currentRate: CurrentRate)

    @Transaction
    @Query("SELECT * FROM currency_info WHERE code = :currencyCode")
    fun getCurrencyInfoWithCurrencyRates(currencyCode: String): CurrencyInfoWithCurrentRates

}