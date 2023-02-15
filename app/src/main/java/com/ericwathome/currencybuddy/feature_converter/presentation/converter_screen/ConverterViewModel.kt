package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericwathome.currencybuddy.common.Resource
import com.ericwathome.currencybuddy.common.UseCases
import com.ericwathome.currencybuddy.feature_converter.domain.model.ExchangeRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _exchangeRate = MutableStateFlow<ConverterState?>(null)
    val exchangeRate = _exchangeRate.asStateFlow()

    suspend fun getExchangeRate(baseCode: String) {
        useCases.getExchangeRate(baseCode).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _exchangeRate.value = ConverterState(data = result.data)
                }
                is Resource.Loading -> {
                    _exchangeRate.value = ConverterState(loading = true)
                }
                is Resource.Error -> {
                    _exchangeRate.value =
                        ConverterState(message = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}