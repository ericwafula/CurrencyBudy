package com.ericwathome.currencybuddy.feature_converter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExchangeRate(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val timeLastUpdateUnix: String,
    val timeLastUpdateUtc: String,
    val timeNextUpdateUnix: String,
    val timeNextUpdateUtc: String,
    val baseCode: String,
    val conversionRates: Map<String, Double>
)