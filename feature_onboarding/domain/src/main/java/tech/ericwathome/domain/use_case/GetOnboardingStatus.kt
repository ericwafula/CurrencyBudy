package tech.ericwathome.domain.use_case

import tech.ericwathome.data.preference.AppPreferences
import javax.inject.Inject

class GetOnboardingStatus @Inject constructor(
    private val preferences: AppPreferences
) {
    suspend operator fun invoke() = preferences.getOnboardingStatus()
}