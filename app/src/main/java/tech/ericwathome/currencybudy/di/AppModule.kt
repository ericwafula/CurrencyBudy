package tech.ericwathome.currencybudy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.currencybudy.network.RateApi
import tech.ericwathome.currencybudy.network.RateClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRateApi(): RateApi = RateClient.rateApi

}