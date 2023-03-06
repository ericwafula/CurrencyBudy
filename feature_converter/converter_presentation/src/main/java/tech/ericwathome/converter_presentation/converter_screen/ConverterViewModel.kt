package tech.ericwathome.converter_presentation.converter_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericwathome.currencybuddy.feature_converter.domain.use_case.GetExchangeRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tech.ericwathome.converter_presentation.util.mapResultData
import tech.ericwathome.data.util.Resource
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val getExchangeRate: GetExchangeRate
) : ViewModel() {
    private var _selectedBase: MutableState<String> = mutableStateOf("EUR")
    var selectedBase: State<String> = _selectedBase
    private var _selectedQuote: MutableState<String> = mutableStateOf("USD")
    var selectedQuote: State<String> = _selectedQuote
    private var _selectedBasePrice: MutableState<String> = mutableStateOf("1")
    var selectedBaseAmount: State<String> = _selectedBasePrice
    private var _converterState = mutableStateOf(ConverterState())
    val converterState: State<ConverterState> = _converterState
    private var _eventFlow = MutableSharedFlow<ConverterEvent>()
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

    fun updateBaseSymbol(symbol: String) {
        _converterState.value = converterState.value.copy(
            data = converterState.value.data?.copy(
                baseSymbol = symbol
            )
        )
    }

    fun updateQuoteSymbol(symbol: String) {
        _converterState.value = converterState.value.copy(
            data = converterState.value.data?.copy(
                quoteSymbol = symbol
            )
        )
    }

    fun convert() {
        viewModelScope.launch {
            getExchangeRate(_selectedBase.value).onEach { result ->
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
                            ConverterEvent.ShowDialog(
                                result.message ?: "An unexpected error occurred"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

}