package com.ericwathome.currencybuddy.feature_converter.domain.use_case

import com.ericwathome.currencybuddy.common.Resource
import com.ericwathome.currencybuddy.feature_converter.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetExchangeRate @Inject constructor(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(baseCode: String) = flow {
        try {
            emit(Resource.Loading())
            val rates = repository.getExchangeRates(baseCode)
            emit(Resource.Success(rates))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "an unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}