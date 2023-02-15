package com.ericwathome.currencybuddy.feature_onboarding.domain.use_case

import com.ericwathome.currencybuddy.common.util.AppPreferences
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOnboardingStatus @Inject constructor(
    private val preferences: AppPreferences
) {
    operator fun invoke() = preferences.showOnboardingPreferenceFlow
}