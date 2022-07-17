package tech.ericwathome.currencybudy.repository

import tech.ericwathome.currencybudy.model.ExchangeRateResponse
import tech.ericwathome.currencybudy.model.PopularRatesResponse
import tech.ericwathome.currencybudy.model.Query
import tech.ericwathome.currencybudy.util.Resource

interface RateRepository {
    suspend fun getExchangeRate(query: HashMap<String, String>): Resource<ExchangeRateResponse>
    suspend fun getPopularRates(query: HashMap<String, String>): Resource<PopularRatesResponse>
}