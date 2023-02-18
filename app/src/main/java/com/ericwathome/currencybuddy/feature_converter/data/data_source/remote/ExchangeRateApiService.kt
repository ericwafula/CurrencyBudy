package com.ericwathome.currencybuddy.feature_converter.data.data_source.remote

import com.ericwathome.currencybuddy.common.Resource
import com.ericwathome.currencybuddy.feature_converter.data.dto.ExchangeRateDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApiService {
    @GET("latest/{baseCode}")
    suspend fun getLatestRates(@Path("baseCode") baseCode: String): ExchangeRateDto
}