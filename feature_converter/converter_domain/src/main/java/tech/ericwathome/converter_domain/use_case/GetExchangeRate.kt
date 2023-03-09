package tech.ericwathome.converter_domain.use_case

import tech.ericwathome.converter_domain.repository.ExchangeRateRepository
import javax.inject.Inject

class GetExchangeRate @Inject constructor(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(baseCode: String) = repository.getExchangeRates(baseCode)
}