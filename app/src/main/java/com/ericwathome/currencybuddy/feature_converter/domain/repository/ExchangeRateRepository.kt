package com.ericwathome.currencybuddy.feature_converter.domain.repository

import com.ericwathome.currencybuddy.common.Resource
import com.ericwathome.currencybuddy.feature_converter.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    suspend fun getExchangeRates(baseCode: String): ExchangeRate
}