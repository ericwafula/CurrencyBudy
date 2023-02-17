package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.runtime.*
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
    val currencies = mutableStateListOf("EUR", "USD", "CAD", "JPY")
    var selectedBaseSymbol = mutableStateOf("€")
    var selectedBase = mutableStateOf("EUR")
    var baseConversionRate = mutableStateOf("1 USD = 0.90 EUR")
    var quoteConversionRate = mutableStateOf("1 EUR = 1.11 USD")
    var selectedQuoteSymbol = mutableStateOf("$")
    var selectedQuote = mutableStateOf("USD")
    var selectedBasePrice = mutableStateOf("120.00")
    var selectedQuotePrice = mutableStateOf("133.70")

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