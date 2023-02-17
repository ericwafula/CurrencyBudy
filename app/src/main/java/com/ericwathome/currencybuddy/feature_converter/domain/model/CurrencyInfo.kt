package com.ericwathome.currencybuddy.feature_converter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "currency_info"
)
data class CurrencyInfo(
    @PrimaryKey(autoGenerate = false)
    val code: String? = null,
    var symbol: String? = null,
    var name: String? = null
)