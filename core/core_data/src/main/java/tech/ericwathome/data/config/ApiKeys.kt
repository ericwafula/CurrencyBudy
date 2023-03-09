package tech.ericwathome.data.config

import javax.inject.Inject

data class ApiKeys @Inject constructor(
    val apiKeysConfig: ApiKeysConfig
)
