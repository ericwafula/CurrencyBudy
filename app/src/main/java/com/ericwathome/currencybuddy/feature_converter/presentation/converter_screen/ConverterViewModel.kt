package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericwathome.currencybuddy.common.Resource
import com.ericwathome.currencybuddy.common.UseCases
import com.ericwathome.currencybuddy.feature_converter.domain.model.relations.CurrencyInfoWithCurrentRates
import com.ericwathome.currencybuddy.feature_converter.presentation.util.mapResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private var _selectedBase: MutableState<String> = mutableStateOf("EUR")
    var selectedBase: State<String> = _selectedBase
    private var _selectedQuote: MutableState<String> = mutableStateOf("USD")
    var selectedQuote: State<String> = _selectedQuote
    private var _selectedBasePrice: MutableState<String> = mutableStateOf("1")
    var selectedBaseAmount: State<String> = _selectedBasePrice
    private var _converterState = mutableStateOf(ConverterState())
    val converterState: State<ConverterState> = _converterState
    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        convert()
    }

    fun updateSelectedBaseCurrency(currency: String) {
        _selectedBase.value = currency
    }

    fun changeSelectedBaseCurrencyPrice(price: String) {
        _selectedBasePrice.value = price
    }

    fun updateSelectedQuoteCurrency(currency: String) {
        _selectedQuote.value = currency
    }


    fun convert() {
        viewModelScope.launch {
            useCases.getExchangeRate(_selectedBase.value).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _converterState.value = converterState.value.copy(
                            data = mapResultData(
                                result.data,
                                selectedBaseAmount.value.toDouble(),
                                selectedQuote.value
                            ),
                            loading = false
                        )
                    }
                    is Resource.Loading -> {
                        _converterState.value = converterState.value.copy(
                            data = mapResultData(
                                result.data,
                                selectedBaseAmount.value.toDouble(),
                                selectedQuote.value
                            ),
                            loading = true
                        )
                    }
                    is Resource.Error -> {
                        _converterState.value = converterState.value.copy(
                            data = mapResultData(
                                result.data,
                                selectedBaseAmount.value.toDouble(),
                                selectedQuote.value
                            ),
                            loading = false
                        )
                        _eventFlow.emit(
                            UiEvent.ShowDialog(
                                result.message ?: "An unexpected error occurred"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UiEvent {
        data class ShowDialog(val message: String) : UiEvent()
    }
}