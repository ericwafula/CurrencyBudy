package tech.ericwathome.domain.use_case

import tech.ericwathome.data.preference.AppPreferencesImpl
import javax.inject.Inject

class GetOnboardingStatus @Inject constructor(
    private val preferences: AppPreferencesImpl
) {
    suspend operator fun invoke() = preferences.getOnboardingStatus()
}