package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import com.ericwathome.currencybuddy.feature_converter.presentation.util.Currency

data class ConverterValues(
    val currencies: List<Currency>?,
    val baseSymbol: String?,
    val baseConversionRate: String?,
    val quoteSymbol: String?,
    val quotePrice: String?,
    val currencyName: String?
)
