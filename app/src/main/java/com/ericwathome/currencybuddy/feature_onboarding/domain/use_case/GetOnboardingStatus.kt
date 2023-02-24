package com.ericwathome.currencybuddy.feature_onboarding.domain.use_case

import com.ericwathome.currencybuddy.common.preference.AppPreferences
import javax.inject.Inject

class GetOnboardingStatus @Inject constructor(
    private val preferences: AppPreferences
) {
    suspend operator fun invoke() = preferences.getOnboardingPreferenceFlow()
}