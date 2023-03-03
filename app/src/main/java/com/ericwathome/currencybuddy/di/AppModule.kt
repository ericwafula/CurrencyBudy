package com.ericwathome.currencybuddy.di

import android.content.Context
import androidx.room.Room
import com.ericwathome.currencybuddy.BuildConfig
import com.ericwathome.currencybuddy.core.util.AppConstants
import com.ericwathome.currencybuddy.core.domain.preference.AppPreferences
import com.ericwathome.currencybuddy.core.domain.use_case.UseCases
import com.ericwathome.currencybuddy.core.data.preference.AppPreferencesImpl
import com.ericwathome.currencybuddy.feature_converter.data.data_source.local.CurrencyBuddyDatabase
import com.ericwathome.currencybuddy.feature_converter.data.data_source.local.ExchangeRateDao
import com.ericwathome.currencybuddy.feature_converter.data.data_source.remote.CurrencyInfoApiService
import com.ericwathome.currencybuddy.feature_converter.data.data_source.remote.ExchangeRateApiService
import com.ericwathome.currencybuddy.feature_converter.domain.repository.ExchangeRateRepository
import com.ericwathome.currencybuddy.feature_converter.domain.use_case.GetExchangeRate
import com.ericwathome.currencybuddy.feature_onboarding.domain.use_case.GetOnboardingStatus
import com.ericwathome.currencybuddy.feature_onboarding.domain.use_case.UpdateOnboardingStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(AppConstants.CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${AppConstants.BASE_URL}${BuildConfig.API_KEY}/")
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
            AppConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideExchangeRateDao(db: CurrencyBuddyDatabase): ExchangeRateDao = db.exchangeRateDao()

    @Provides
    fun provideGetExchangeRateUseCase(repository: ExchangeRateRepository) =
        GetExchangeRate(repository)

    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences = AppPreferencesImpl(context)

    @Provides
    fun provideUpdateOnboardingStatusUseCase(appPreferences: AppPreferences) =
        UpdateOnboardingStatus(appPreferences)

    @Provides
    fun provideGetOnboardingStatusUseCase(appPreferences: AppPreferences) =
        GetOnboardingStatus(appPreferences)

    @Provides
    fun provideUseCases(
        getExchangeRate: GetExchangeRate,
        updateOnboardingStatus: UpdateOnboardingStatus,
        getOnboardingStatus: GetOnboardingStatus
    ): UseCases {
        return UseCases(
            getExchangeRate = getExchangeRate,
            updateOnboardingStatus = updateOnboardingStatus,
            getOnboardingStatus = getOnboardingStatus
        )
    }

}