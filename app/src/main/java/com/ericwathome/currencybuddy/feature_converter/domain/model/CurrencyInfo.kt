package com.ericwathome.currencybuddy.feature_converter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CurrencyInfo(
    @PrimaryKey
    val id: Long? = null,
    val code: String,
    val symbol: String,
    val name: String,
    val namePlural: String,
)