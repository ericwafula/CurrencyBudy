package tech.ericwathome.converter_data.data_source.remote

import tech.ericwathome.converter_data.dto.ExchangeRateDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApiService {
    @GET("latest/{baseCode}")
    suspend fun getLatestRates(@Path("baseCode") baseCode: String): ExchangeRateDto
}