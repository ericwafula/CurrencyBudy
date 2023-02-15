package com.ericwathome.currencybuddy.common

import com.ericwathome.currencybuddy.feature_converter.domain.use_case.GetExchangeRate

data class UseCases(
    val getExchangeRate: GetExchangeRate
)
