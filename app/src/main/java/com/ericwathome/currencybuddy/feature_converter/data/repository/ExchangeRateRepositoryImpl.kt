package com.ericwathome.currencybuddy.feature_converter.data.repository

import com.ericwathome.currencybuddy.feature_converter.data.data_source.remote.ExchangeRateApiService
import com.ericwathome.currencybuddy.feature_converter.data.dto.toExchangeRate
import com.ericwathome.currencybuddy.feature_converter.domain.model.ExchangeRate
import com.ericwathome.currencybuddy.feature_converter.domain.repository.ExchangeRateRepository
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val apiService: ExchangeRateApiService
) : ExchangeRateRepository {
    override suspend fun getExchangeRates(baseCode: String): ExchangeRate {
        return apiService.getLatestRates(baseCode).toExchangeRate()
    }
}