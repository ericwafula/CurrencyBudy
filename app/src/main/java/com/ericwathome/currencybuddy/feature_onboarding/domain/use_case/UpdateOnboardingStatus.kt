package com.ericwathome.currencybuddy.feature_onboarding.domain.use_case

import com.ericwathome.currencybuddy.common.util.AppPreferences
import javax.inject.Inject

class UpdateOnboardingStatus @Inject constructor(
    private val appPreferences: AppPreferences
) {
    suspend operator fun invoke(showOnboarding: Boolean) {
        appPreferences.setShowOnboarding(showOnboarding)
    }
}