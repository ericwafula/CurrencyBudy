package tech.ericwathome.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.converter_domain.repository.ExchangeRateRepository
import tech.ericwathome.data.repository.ExchangeRateRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindExchangeRateRepository(
        repositoryImpl: ExchangeRateRepositoryImpl
    ): ExchangeRateRepository

}