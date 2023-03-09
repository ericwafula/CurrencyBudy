package tech.ericwathome.onboarding_domain.use_case

import tech.ericwathome.data.preference.AppPreferences
import javax.inject.Inject

class UpdateOnboardingStatus @Inject constructor(
    private val preferences: AppPreferences
) {
    suspend operator fun invoke(showOnboarding: Boolean) {
        preferences.updateOnboardingStatus(showOnboarding)
    }
}