package com.ericwathome.currencybuddy.common.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.datastore by preferencesDataStore(
    PreferenceConstants.PREFERENCES
)
class AppPreferences(
    private val context: Context
) {

    companion object {
        val SHOW_ONBOARDING = booleanPreferencesKey(PreferenceConstants.SHOW_ONBOARDING_PREFERENCE_KEY)
    }

    suspend fun setShowOnboarding(showOnboarding: Boolean) {
        context.datastore.edit { preferences ->
            preferences[SHOW_ONBOARDING] = showOnboarding
        }
    }

    val showOnboardingPreferenceFlow = context.datastore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map {  preferences ->
        preferences[SHOW_ONBOARDING] ?: false
    }

}