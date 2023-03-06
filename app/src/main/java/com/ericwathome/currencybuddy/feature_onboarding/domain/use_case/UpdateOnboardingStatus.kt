package com.ericwathome.currencybuddy.feature_onboarding.domain.use_case

import tech.ericwathome.data.preference.AppPreferences
import javax.inject.Inject

class UpdateOnboardingStatus @Inject constructor(
    private val appPreferences: AppPreferences
) {
    suspend operator fun invoke(showOnboarding: Boolean) {
        appPreferences.updateOnboardingStatus(showOnboarding)
    }
}