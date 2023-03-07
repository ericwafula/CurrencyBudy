package tech.ericwathome.data.preference

import kotlinx.coroutines.flow.Flow

interface AppPreferences {
    suspend fun updateOnboardingStatus(showOnboarding: Boolean)
    fun getOnboardingStatus(): Flow<Boolean>
}