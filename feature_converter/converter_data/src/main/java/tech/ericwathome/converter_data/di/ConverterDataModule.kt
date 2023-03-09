package tech.ericwathome.converter_data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.ericwathome.converter_domain.repository.ExchangeRateRepository
import tech.ericwathome.converter_data.data_source.local.CurrencyBuddyDatabase
import tech.ericwathome.converter_data.data_source.local.ExchangeRateDao
import tech.ericwathome.converter_data.data_source.remote.CurrencyInfoApiService
import tech.ericwathome.converter_data.data_source.remote.ExchangeRateApiService
import tech.ericwathome.converter_data.repository.ExchangeRateRepositoryImpl
import tech.ericwathome.converter_data.util.Constants
import tech.ericwathome.data.config.ApiKeys
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConverterDataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(Constants.CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, apiKeys: ApiKeys): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${Constants.BASE_URL}${apiKeys.apiKeysConfig.getExchangeRateApiKey()}/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeRateApiService(retrofit: Retrofit): ExchangeRateApiService {
        return retrofit.create(ExchangeRateApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyInfoApiService(retrofit: Retrofit): CurrencyInfoApiService {
        return retrofit.create(CurrencyInfoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyBuddyDatabase(@ApplicationContext context: Context): CurrencyBuddyDatabase {
        return Room.databaseBuilder(
            context,
            CurrencyBuddyDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideExchangeRateDao(db: CurrencyBuddyDatabase): ExchangeRateDao = db.exchangeRateDao()

    @Provides
    @Singleton
    fun provideExchangeRateRepository(
        exchangeRateApiService: ExchangeRateApiService,
        currencyInfoApiService: CurrencyInfoApiService,
        exchangeRateDao: ExchangeRateDao
    ): ExchangeRateRepository = ExchangeRateRepositoryImpl(
        exchangeRateApiService = exchangeRateApiService,
        currencyInfoApiService = currencyInfoApiService,
        dao = exchangeRateDao
    )

}