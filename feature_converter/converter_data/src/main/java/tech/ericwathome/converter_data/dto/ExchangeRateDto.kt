package tech.ericwathome.converter_data.dto

import tech.ericwathome.converter_data.model.ExchangeRate
import com.google.gson.annotations.SerializedName

data class ExchangeRateDto(
    val result: String,
    val documentation: String,
    @SerializedName("terms_of_use")
    val termsOfUse: String,
    @SerializedName("time_last_update_unix")
    val timeLastUpdateUnix: String,
    @SerializedName("time_last_update_utc")
    val timeLastUpdateUtc: String,
    @SerializedName("time_next_update_unix")
    val timeNextUpdateUnix: String,
    @SerializedName("time_next_update_utc")
    val timeNextUpdateUtc: String,
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("conversion_rates")
    val conversionRates: Map<String, Double>
)

fun ExchangeRateDto.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        timeLastUpdateUnix = timeLastUpdateUnix,
        timeLastUpdateUtc = timeLastUpdateUtc,
        timeNextUpdateUnix = timeNextUpdateUnix,
        timeNextUpdateUtc = timeNextUpdateUtc,
        baseCode = baseCode,
        conversionRates = conversionRates
    )
}
