package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen

import androidx.lifecycle.ViewModel
import com.ericwathome.currencybuddy.common.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
}