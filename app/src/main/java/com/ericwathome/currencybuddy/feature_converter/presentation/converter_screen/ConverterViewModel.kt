package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import java.util.Currency
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _exchangeRate = MutableStateFlow<ConverterState?>(null)
    val exchangeRate = _exchangeRate.asStateFlow()
    private val _currencies: SnapshotStateList<String> = mutableStateListOf("EUR", "USD", "CAD", "JPY")
    val currencies: List<String> = _currencies
    private var _selectedBaseSymbol: MutableState<String> = mutableStateOf("€")
    var selectedBaseSymbol: State<String> = _selectedBaseSymbol
    private var _selectedBase: MutableState<String> = mutableStateOf("EUR")
    var selectedBase: State<String> = _selectedBase
    private var _baseConversionRate: MutableState<String> = mutableStateOf("1 USD = 0.90 EUR")
    var baseConversionRate: State<String> = _baseConversionRate
    private var _quoteConversionRate: MutableState<String> = mutableStateOf("1 EUR = 1.11 USD")
    var quoteConversionRate: State<String> = _quoteConversionRate
    private var _selectedQuoteSymbol: MutableState<String> = mutableStateOf("$")
    var selectedQuoteSymbol: State<String> = _selectedQuoteSymbol
    private var _selectedQuote: MutableState<String> = mutableStateOf("USD")
    var selectedQuote: State<String> = _selectedQuote
    private var _selectedBasePrice: MutableState<String> = mutableStateOf("120.00")
    var selectedBasePrice: State<String> = _selectedBasePrice
    private var _selectedQuotePrice: MutableState<String> = mutableStateOf("133.70")
    var selectedQuotePrice: State<String> = _selectedQuotePrice

    fun updateSelectedBaseCurrency(currency: String) {
        _selectedBaseSymbol.value = currency
    }

    fun updateSelectedQuoteCurrency(currency: String) {
        _selectedBaseSymbol.value = currency
    }

    fun changeSelectedBaseCurrencyPrice(price: String) {
        _selectedBasePrice.value = price
    }

    fun changeSelectedQuoteCurrencyPrice(price: String) {
        _selectedQuotePrice.value = price
    }

    fun convert() {

    }

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