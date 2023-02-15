package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import com.ericwathome.currencybuddy.feature_converter.domain.model.ExchangeRate

data class ConverterState(
    var data: ExchangeRate? = null,
    var message: String = "",
    var loading: Boolean = false
)
