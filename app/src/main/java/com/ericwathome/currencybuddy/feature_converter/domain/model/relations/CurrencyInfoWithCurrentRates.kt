package com.ericwathome.currencybuddy.feature_converter.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrencyInfo
import com.ericwathome.currencybuddy.feature_converter.domain.model.CurrentRate

data class CurrencyInfoWithCurrentRates (
    @Embedded val currencyInfo: CurrencyInfo,
    @Relation(
        parentColumn = "code",
        entityColumn = "currencyInfoCode"
    )
    val rates: List<CurrentRate>
)