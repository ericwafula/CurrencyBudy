package tech.ericwathome.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import tech.ericwathome.data.model.CurrencyInfo
import tech.ericwathome.data.model.CurrentRate

data class CurrencyInfoWithCurrentRates (
    @Embedded val currencyInfo: CurrencyInfo,
    @Relation(
        parentColumn = "code",
        entityColumn = "currencyInfoCode"
    )
    val rates: List<CurrentRate>
)