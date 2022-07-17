package tech.ericwathome.currencybudy.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(
    val success: String,
    val query: Query,
    val info: Info,
    val date: String,
    val result: Float
)

data class Query(
    val from: String,
    val to: String,
    val amount: Int
)

data class Info(
    val timeStamp: Long,
    val rate: Float
)

data class PopularRatesResponse(
    val success: String,
    val base: String,
    val rates: PopularRates
)

data class PopularRates(
    @SerializedName("ZAR") val zar: String,
    @SerializedName("RUB") val rub: String,
    @SerializedName("MXN") val mxn: String,
    @SerializedName("EUR") val eur: String,
    @SerializedName("GBP") val gbp: String,
    @SerializedName("JPY") val jpy: String,
    @SerializedName("CAD") val cad: String
)
