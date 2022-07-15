package tech.ericwathome.currencybudy.repository

import retrofit2.Response
import tech.ericwathome.currencybudy.model.RateDetails
import tech.ericwathome.currencybudy.util.Resource

interface RateRepository {
    suspend fun getExchangeRate(query: HashMap<String, String>): Resource<RateDetails>
}