package tech.ericwathome.converter_data.dto

import tech.ericwathome.converter_data.model.CurrencyInfo
import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    val symbol: String,
    val name: String,
    @SerializedName("symbol_native")
    val symbolNative: String,
    @SerializedName("decimal_digits")
    val decimalDigits: Int,
    val rounding: Double,
    val code: String,
    @SerializedName("name_plural")
    val namePlural: String
)

fun CurrencyDto.toCurrency(): CurrencyInfo {
    return CurrencyInfo(
        symbol = symbol,
        name = name,
        code = code
    )
}