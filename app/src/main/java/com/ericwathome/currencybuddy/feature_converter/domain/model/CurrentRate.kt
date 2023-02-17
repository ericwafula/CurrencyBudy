package com.ericwathome.currencybuddy.feature_converter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentRate(
    @PrimaryKey
    val id: Long? = null,
    val code: String,
    val name: String,
    val rate: Double,
    val currencyInfoId: Long
)
