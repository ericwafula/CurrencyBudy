package com.ericwathome.currencybuddy.feature_converter.data.data_source.local


abstract class CurrencyBuddyDatabase {
    abstract fun exchangeRateDao(): ExchangeRateDao
}