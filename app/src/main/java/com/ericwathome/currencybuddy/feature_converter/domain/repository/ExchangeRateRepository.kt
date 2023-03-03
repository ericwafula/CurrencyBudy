package com.ericwathome.currencybuddy.feature_converter.domain.repository

import com.ericwathome.currencybuddy.core.util.Resource
import com.ericwathome.currencybuddy.feature_converter.domain.model.relations.CurrencyInfoWithCurrentRates
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    suspend fun getExchangeRates(baseCode: String): Flow<Resource<CurrencyInfoWithCurrentRates>>
}