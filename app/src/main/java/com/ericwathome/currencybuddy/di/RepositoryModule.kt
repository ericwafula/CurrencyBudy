package com.ericwathome.currencybuddy.di

import com.ericwathome.currencybuddy.feature_converter.data.repository.ExchangeRateRepositoryImpl
import com.ericwathome.currencybuddy.feature_converter.domain.repository.ExchangeRateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindExchangeRateRepository(
        repositoryImpl: ExchangeRateRepositoryImpl
    ): ExchangeRateRepository

}