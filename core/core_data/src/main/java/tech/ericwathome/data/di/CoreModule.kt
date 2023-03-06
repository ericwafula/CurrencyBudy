package tech.ericwathome.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.data.preference.AppPreferences
import tech.ericwathome.data.preference.AppPreferencesImpl

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferencesImpl(context)
    }

}