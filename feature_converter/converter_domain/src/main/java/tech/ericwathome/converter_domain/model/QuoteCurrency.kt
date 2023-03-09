package tech.ericwathome.converter_domain.model

data class QuoteCurrency(
    val code: String,
    val name: String,
    val rate: Double,
    val symbol: String
)
