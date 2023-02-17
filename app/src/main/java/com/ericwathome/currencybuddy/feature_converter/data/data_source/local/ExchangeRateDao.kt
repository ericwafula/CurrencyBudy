package com.ericwathome.currencybuddy.feature_converter.data.data_source.local

import androidx.room.*
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrencyInfo
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrentRate
import com.ericwathome.currencybuddy.feature_converter.domain.model.ExchangeRate
import com.ericwathome.currencybuddy.feature_converter.domain.model.relations.CurrencyInfoWithCurrentRates
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyInfo(currencyInfo: CurrencyInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyRate(currentRate: CurrentRate)

    @Transaction
    @Query("SELECT * FROM currency_info WHERE code = :currencyCode")
    fun getCurrencyInfoWithCurrencyRates(currencyCode: String): Flow<CurrencyInfoWithCurrentRates>

}