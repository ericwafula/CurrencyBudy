package com.ericwathome.currencybuddy.core.domain.preference

import kotlinx.coroutines.flow.Flow

interface AppPreferences {
    suspend fun updateOnboardingStatus(showOnboarding: Boolean)

    suspend fun getOnboardingPreferenceFlow(): Flow<Boolean>
}