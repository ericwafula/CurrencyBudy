package tech.ericwathome.converter_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.converter_domain.repository.ExchangeRateRepository
import tech.ericwathome.converter_domain.use_case.GetExchangeRate

@Module
@InstallIn(SingletonComponent::class)
object ExchangeRateModule {

//    @Provides
//    fun provideGetExchangeRateUseCase(repository: ExchangeRateRepository): GetExchangeRate {
//        return GetExchangeRate(repository)
//    }

}