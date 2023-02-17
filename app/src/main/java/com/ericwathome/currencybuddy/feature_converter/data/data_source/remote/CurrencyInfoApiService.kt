package com.ericwathome.currencybuddy.feature_converter.data.data_source.remote

import com.ericwathome.currencybuddy.feature_converter.data.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Url

interface CurrencyInfoApiService {
    @GET
    suspend fun getCurrencyInfo(@Url url: String): List<CurrencyDto>
}