package tech.ericwathome.currencybudy.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import tech.ericwathome.currencybudy.model.RateDetails

interface RateApi {
    @GET("/exchangerates_data/convert")
    fun getExchangeRates(
        @QueryMap query: HashMap<String, String>
    ): Response<RateDetails>
}