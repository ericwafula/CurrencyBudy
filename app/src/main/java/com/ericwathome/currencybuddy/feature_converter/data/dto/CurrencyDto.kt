package com.ericwathome.currencybuddy.feature_converter.data.dto

import com.ericwathome.currencybuddy.feature_converter.domain.model.Currency
import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    val symbol: String,
    val name: String,
    @SerializedName("symbol_native")
    val symbolNative: String,
    @SerializedName("decimal_digits")
    val decimalDigits: Int,
    val rounding: Int,
    val code: String,
    @SerializedName("name_plural")
    val namePlural: String
)

fun CurrencyDto.toCurrency(): Currency {
    return Currency(
        symbol = symbol,
        name = name,
        code = code,
        namePlural = namePlural
    )
}