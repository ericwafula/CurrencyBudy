package tech.ericwathome.data.data_source.remote

import tech.ericwathome.data.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Url

interface CurrencyInfoApiService {
    @GET
    suspend fun getCurrencyInfo(@Url url: String): List<CurrencyDto>
}