package tech.ericwathome.presentation.onboarding_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.ericwathome.data.preference.AppPreferences
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : ViewModel() {

    fun updateOnboardingState(showOnboarding: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.updateOnboardingStatus(showOnboarding)
        }
    }

}