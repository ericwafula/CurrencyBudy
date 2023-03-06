package com.ericwathome.currencybuddy.presentation

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import tech.ericwathome.data.preference.AppPreferences
import tech.ericwathome.data.preference.AppPreferencesImpl
import tech.ericwathome.presentation.Screens
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    preferences: AppPreferences
) : ViewModel() {

//    @Module
//    @InstallIn(ViewModelComponent::class)
//    object ViewModelModule {
//        @Provides
//        fun provideAppPreferences(
//            @ApplicationContext context: Context
//        ): AppPreferences {
//            return AppPreferencesImpl(context)
//        }
//    }

    private var _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private var _startDestination: MutableState<String> = mutableStateOf(Screens.Onboarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            preferences.getOnboardingStatus().collect { isCompleted ->
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