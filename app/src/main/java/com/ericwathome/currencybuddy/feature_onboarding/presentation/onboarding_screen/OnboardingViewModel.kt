package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericwathome.currencybuddy.core.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun updateOnboardingState(showOnboarding: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.updateOnboardingStatus(showOnboarding)
        }
    }

}