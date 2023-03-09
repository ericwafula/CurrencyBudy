package tech.ericwathome.converter_domain.model

data class BaseCurrencyVsQuoteCurrencies(
    val baseCurrency: BaseCurrency,
    val quoteCurrencies: List<QuoteCurrency>
)
