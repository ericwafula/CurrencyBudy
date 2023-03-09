package tech.ericwathome.converter_presentation.converter_screen

import tech.ericwathome.converter_presentation.util.Currency

data class ConverterValues(
    val currencies: List<Currency>?,
    val baseSymbol: String?,
    val baseConversionRate: String?,
    val quoteSymbol: String?,
    val quotePrice: String?,
    val currencyName: String?,
    val accountNumber: String,
    val expiryDate: String,
    val currentBaseVsQuote: String
)
