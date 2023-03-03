package com.ericwathome.currencybuddy.core.domain.use_case

import com.ericwathome.currencybuddy.feature_converter.domain.use_case.GetExchangeRate
import com.ericwathome.currencybuddy.feature_onboarding.domain.use_case.GetOnboardingStatus
import com.ericwathome.currencybuddy.feature_onboarding.domain.use_case.UpdateOnboardingStatus

data class UseCases(
    val getExchangeRate: GetExchangeRate,
    val updateOnboardingStatus: UpdateOnboardingStatus,
    val getOnboardingStatus: GetOnboardingStatus
)
