package tech.ericwathome.currencybudy.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import tech.ericwathome.currencybudy.model.ExchangeRateResponse
import tech.ericwathome.currencybudy.model.PopularRatesResponse

interface RateApi {
    @GET("/exchangerates_data/convert")
    suspend fun getExchangeRate(
        @QueryMap query: HashMap<String, String>
    ): Response<ExchangeRateResponse>

    @GET("/exchangerates_data/latest")
    suspend fun getPopularRates(
        @QueryMap query: HashMap<String, String>
    ): Response<PopularRatesResponse>
}