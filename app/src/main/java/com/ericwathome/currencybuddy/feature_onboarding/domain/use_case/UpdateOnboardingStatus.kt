package com.ericwathome.currencybuddy.feature_onboarding.domain.use_case

import com.ericwathome.currencybuddy.common.preference.AppPreferences
import javax.inject.Inject

class UpdateOnboardingStatus @Inject constructor(
    private val appPreferences: AppPreferences
) {
    suspend operator fun invoke(showOnboarding: Boolean) {
        appPreferences.updateOnboardingStatus(showOnboarding)
    }
}