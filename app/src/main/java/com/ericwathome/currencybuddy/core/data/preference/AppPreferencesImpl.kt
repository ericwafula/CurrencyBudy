package com.ericwathome.currencybuddy.core.data.preference

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.ericwathome.currencybuddy.core.domain.preference.AppPreferences
import com.ericwathome.currencybuddy.core.util.PreferenceConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.datastore by preferencesDataStore(
    PreferenceConstants.PREFERENCES
)
class AppPreferencesImpl  (
    private val context: Context
) : AppPreferences {

    companion object {
        val SHOW_ONBOARDING = booleanPreferencesKey(PreferenceConstants.SHOW_ONBOARDING_PREFERENCE_KEY)
    }

    override suspend fun updateOnboardingStatus(showOnboarding: Boolean) {
        context.datastore.edit { preferences ->
            preferences[SHOW_ONBOARDING] = showOnboarding
        }
    }

    override suspend fun getOnboardingPreferenceFlow(): Flow<Boolean> {
        return context.datastore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {  preference ->
                preference[SHOW_ONBOARDING] ?: false
            }
    }

}