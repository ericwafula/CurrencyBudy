package com.ericwathome.currencybuddy.config

import com.ericwathome.currencybuddy.BuildConfig
import tech.ericwathome.data.config.ApiKeysConfig

class ApiKeysConfigImpl : ApiKeysConfig {
    override fun getExchangeRateApiKey(): String {
        return BuildConfig.API_KEY
    }
}