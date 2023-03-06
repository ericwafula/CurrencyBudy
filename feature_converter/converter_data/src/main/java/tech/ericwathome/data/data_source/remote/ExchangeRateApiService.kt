package tech.ericwathome.data.data_source.remote

import tech.ericwathome.data.dto.ExchangeRateDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApiService {
    @GET("latest/{baseCode}")
    suspend fun getLatestRates(@Path("baseCode") baseCode: String): ExchangeRateDto
}