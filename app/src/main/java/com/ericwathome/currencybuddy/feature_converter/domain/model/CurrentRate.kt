package com.ericwathome.currencybuddy.feature_converter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentRate(
    @PrimaryKey(autoGenerate = false)
    val code: String,
    val name: String? = null,
    val rate: Double? = null,
    val symbol: String? = null,
    val currencyInfoCode: String
)
