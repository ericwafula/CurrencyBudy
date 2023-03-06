package tech.ericwathome.converter_domain.repository

import kotlinx.coroutines.flow.Flow
import tech.ericwathome.data.model.relations.CurrencyInfoWithCurrentRates
import tech.ericwathome.data.util.Resource

interface ExchangeRateRepository {
    suspend fun getExchangeRates(baseCode: String): Flow<Resource<CurrencyInfoWithCurrentRates>>
}