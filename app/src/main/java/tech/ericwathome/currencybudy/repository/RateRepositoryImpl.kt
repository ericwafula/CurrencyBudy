package tech.ericwathome.currencybudy.repository

import tech.ericwathome.currencybudy.model.ExchangeRateResponse
import tech.ericwathome.currencybudy.model.PopularRatesResponse
import tech.ericwathome.currencybudy.network.RateApi
import tech.ericwathome.currencybudy.util.Resource
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor (private val rateApi: RateApi) : RateRepository {
    override suspend fun getExchangeRate(query: HashMap<String, String>): Resource<ExchangeRateResponse> {
        return try {
            val response = rateApi.getExchangeRate(query)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error Message!")
        }
    }

    override suspend fun getPopularRates(query: HashMap<String, String>): Resource<PopularRatesResponse> {
        return try {
            val response = rateApi.getPopularRates(query)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error Message!")
        }
    }
}