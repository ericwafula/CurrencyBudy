package com.ericwathome.currencybuddy.feature_converter.presentation.util

import com.ericwathome.currencybuddy.feature_converter.domain.model.relations.CurrencyInfoWithCurrentRates
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.ConverterValues
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.ConverterViewModel

internal fun mapResultData(
    result: CurrencyInfoWithCurrentRates?,
    amount: Double,
    currencyCode: String
): ConverterValues {
    val currencyList = result?.rates?.map {
        it.code
    }
    val baseCode = result?.currencyInfo?.code ?: ""
    val baseSymbol = result?.currencyInfo?.symbol ?: ""
    val currentRateObject = result?.rates?.find { it.code == currencyCode }
    val quoteCode = currentRateObject?.code ?: ""
    val baseConversionRate = currentRateObject?.rate ?: 0.0
    val quoteSymbol = currentRateObject?.symbol ?: ""
    val quotePrice = "%.4f".format((amount * baseConversionRate))
    return ConverterValues(
        currencies = currencyList,
        baseSymbol = baseSymbol,
        baseConversionRate = "1 $baseCode = $baseConversionRate $quoteCode",
        quoteSymbol = quoteSymbol,
        quotePrice = quotePrice.toString()
    )
}