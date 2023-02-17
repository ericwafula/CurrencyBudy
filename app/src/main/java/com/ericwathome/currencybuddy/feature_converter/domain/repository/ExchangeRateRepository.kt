package com.ericwathome.currencybuddy.feature_converter.domain.repository

import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    suspend fun getExchangeRate(baseCode: String): Flow<CurrencyInfo>
}