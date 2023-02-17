package com.ericwathome.currencybuddy.feature_converter.data.data_source.remote

import com.ericwathome.currencybuddy.feature_converter.data.dto.CurrencyDto
import retrofit2.http.GET

interface CurrencyInfoApiService {
    @GET("9a6d116c-65f6-4032-bde4-f60ccee92cc7")
    suspend fun getCurrencyInfo(): List<CurrencyDto>
}