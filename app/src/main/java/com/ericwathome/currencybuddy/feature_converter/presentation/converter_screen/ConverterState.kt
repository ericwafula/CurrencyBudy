package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import com.ericwathome.currencybuddy.feature_converter.domain.model.relations.CurrencyInfoWithCurrentRates

data class ConverterState(
    var data: ConverterValues? = null,
    var message: String = "",
    var loading: Boolean = false
)
