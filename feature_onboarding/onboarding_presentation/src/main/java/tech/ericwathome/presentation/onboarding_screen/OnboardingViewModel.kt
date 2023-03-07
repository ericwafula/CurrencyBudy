package tech.ericwathome.presentation.onboarding_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.ericwathome.data.preference.AppPreferencesImpl
import javax.inject.Inject

class OnboardingViewModel : ViewModel() {

    @Inject
    lateinit var appPreferences: AppPreferencesImpl

    fun updateOnboardingState(showOnboarding: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.updateOnboardingStatus(showOnboarding)
        }
    }

}