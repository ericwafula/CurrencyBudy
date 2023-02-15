package com.ericwathome.currencybuddy.feature_converter.data.data_source.remote

import com.ericwathome.currencybuddy.feature_converter.data.dto.ExchangeRateDto
import retrofit2.http.GET

interface ExchangeRateApiService {
    @GET("{apiKey}/latest/{baseCode}")
    suspend fun getLatestRates(apiKey: String, baseCode: String): ExchangeRateDto
}