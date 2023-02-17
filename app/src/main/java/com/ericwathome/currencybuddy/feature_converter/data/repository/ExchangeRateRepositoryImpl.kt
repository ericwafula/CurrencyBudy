package com.ericwathome.currencybuddy.feature_converter.data.repository

import android.icu.util.Currency
import com.ericwathome.currencybuddy.feature_converter.data.data_source.remote.ExchangeRateApiService
import com.ericwathome.currencybuddy.feature_converter.data.dto.toExchangeRate
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrencyInfo
import com.ericwathome.currencybuddy.feature_converter.domain.model.ExchangeRate
import com.ericwathome.currencybuddy.feature_converter.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val apiService: ExchangeRateApiService
) : ExchangeRateRepository {
    override suspend fun getExchangeRate(baseCode: String): Flow<CurrencyInfo> {
        TODO("Not yet implemented")
    }

}