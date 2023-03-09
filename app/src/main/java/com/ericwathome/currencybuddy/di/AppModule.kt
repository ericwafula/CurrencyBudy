package com.ericwathome.currencybuddy.di

import com.ericwathome.currencybuddy.config.ApiKeysConfigImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.data.config.ApiKeysConfig

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiKeys(): ApiKeysConfig = ApiKeysConfigImpl()

}