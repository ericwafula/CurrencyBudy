package com.ericwathome.currencybuddy.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



//    @Provides
//    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
//        return AppPreferencesImpl(context)
//    }

}