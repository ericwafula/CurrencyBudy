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

}