package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

data class ConverterValues(
    val currencies: List<String>?,
    val baseSymbol: String?,
    val baseConversionRate: String?,
    val quoteSymbol: String?,
    val quotePrice: String?
)
