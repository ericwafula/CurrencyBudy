package tech.ericwathome.converter_domain.use_case

import dagger.hilt.android.scopes.ViewModelScoped
import tech.ericwathome.converter_domain.repository.ExchangeRateRepository
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class GetExchangeRate @Inject constructor(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(baseCode: String) = repository.getExchangeRates(baseCode)
}