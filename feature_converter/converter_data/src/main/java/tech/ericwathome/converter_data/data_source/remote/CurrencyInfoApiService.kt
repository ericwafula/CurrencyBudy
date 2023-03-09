package tech.ericwathome.converter_data.data_source.remote

import tech.ericwathome.converter_data.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Url

interface CurrencyInfoApiService {
    @GET
    suspend fun getCurrencyInfo(@Url url: String): List<CurrencyDto>
}