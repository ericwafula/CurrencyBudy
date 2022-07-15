package tech.ericwathome.currencybudy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import tech.ericwathome.currencybudy.network.RateApi
import tech.ericwathome.currencybudy.network.RateClient
import tech.ericwathome.currencybudy.repository.RateRepository
import tech.ericwathome.currencybudy.repository.RateRepositoryImpl
import tech.ericwathome.currencybudy.util.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRateApi(): RateApi = RateClient.rateApi

    @Provides
    fun provideRateRepository(rateApi: RateApi): RateRepository = RateRepositoryImpl(rateApi)

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }

}