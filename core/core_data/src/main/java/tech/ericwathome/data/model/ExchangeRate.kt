package tech.ericwathome.data.model


data class ExchangeRate(
    val timeLastUpdateUnix: String,
    val timeLastUpdateUtc: String,
    val timeNextUpdateUnix: String,
    val timeNextUpdateUtc: String,
    val baseCode: String,
    val conversionRates: Map<String, Double>
)