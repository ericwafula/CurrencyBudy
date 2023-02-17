package com.ericwathome.currencybuddy.feature_converter.domain.model


data class Currency(
    val symbol: String,
    val name: String,
    val code: String,
    val namePlural: String
)
