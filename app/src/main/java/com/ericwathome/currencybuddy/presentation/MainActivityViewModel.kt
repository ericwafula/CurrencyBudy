package com.ericwathome.currencybuddy.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tech.ericwathome.data.preference.AppPreferences
import tech.ericwathome.data.preference.AppPreferencesImpl
import tech.ericwathome.presentation.Screens
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferences: AppPreferences
) : ViewModel() {

    private var _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private var _startDestination: MutableState<String> = mutableStateOf(Screens.Onboarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            preferences.getOnboardingStatus().collect { isCompleted ->
                if (isCompleted) {
                    _startDestination.value = Screens.SignIn.route
                } else {
                    _startDestination.value = Screens.Onboarding.route
                }
            }
            _isLoading.value = false
        }
    }

}