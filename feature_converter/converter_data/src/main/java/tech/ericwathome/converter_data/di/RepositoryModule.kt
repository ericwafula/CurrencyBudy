package tech.ericwathome.converter_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.converter_data.repository.ExchangeRateRepositoryImpl
import tech.ericwathome.converter_domain.repository.ExchangeRateRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindExchangeRateRepository(
        repositoryImpl: ExchangeRateRepositoryImpl
    ): ExchangeRateRepository

}