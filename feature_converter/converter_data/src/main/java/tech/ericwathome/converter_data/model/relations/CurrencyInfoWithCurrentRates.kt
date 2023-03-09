package tech.ericwathome.converter_data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import tech.ericwathome.converter_data.model.CurrencyInfo
import tech.ericwathome.converter_data.model.CurrentRate
import tech.ericwathome.converter_domain.model.BaseCurrency
import tech.ericwathome.converter_domain.model.BaseCurrencyVsQuoteCurrencies
import tech.ericwathome.converter_domain.model.QuoteCurrency

data class CurrencyInfoWithCurrentRates (
    @Embedded val currencyInfo: CurrencyInfo,
    @Relation(
        parentColumn = "code",
        entityColumn = "currencyInfoCode"
    )
    val rates: List<CurrentRate>
)

fun CurrencyInfoWithCurrentRates.toBaseCurrencyVsQuoteCurrencies(): BaseCurrencyVsQuoteCurrencies {
    return BaseCurrencyVsQuoteCurrencies(
        baseCurrency = BaseCurrency(
            code = currencyInfo.code,
            symbol = currencyInfo.symbol ?: "",
            name = currencyInfo.name ?: ""
        ),
        quoteCurrencies = rates.map { currentRate ->
            QuoteCurrency(
                code = currentRate.code,
                name = currentRate.name ?: "",
                rate = currentRate.rate ?: 0.0,
                symbol = currentRate.symbol ?: ""
            )
        }
    )
}