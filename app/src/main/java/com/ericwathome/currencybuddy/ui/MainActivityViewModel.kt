package com.ericwathome.currencybuddy.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericwathome.currencybuddy.common.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {

    private var _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private var _startDestination: MutableState<String> = mutableStateOf(Screens.Onboarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            useCases.getOnboardingStatus().collect { isCompleted ->
                if (isCompleted) {
                    _startDestination.value = Screens.Converter.route
                } else {
                    _startDestination.value = Screens.Onboarding.route
                }
            }
            _isLoading.value = false
        }
    }

}