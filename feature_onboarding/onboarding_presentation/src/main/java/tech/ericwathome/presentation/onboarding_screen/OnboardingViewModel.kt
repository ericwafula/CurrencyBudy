package tech.ericwathome.presentation.onboarding_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.ericwathome.data.preference.AppPreferences
import tech.ericwathome.data.preference.AppPreferencesImpl
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