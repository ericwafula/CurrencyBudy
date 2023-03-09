package tech.ericwathome.onboarding_domain.use_case

import tech.ericwathome.data.preference.AppPreferences
import javax.inject.Inject

class GetOnboardingStatus @Inject constructor(
    private val preferences: AppPreferences
) {
    operator fun invoke() = preferences.getOnboardingStatus()
}