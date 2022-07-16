package tech.ericwathome.currencybudy.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import tech.ericwathome.currencybudy.repository.RateRepository
import tech.ericwathome.currencybudy.util.DispatcherProvider
import tech.ericwathome.currencybudy.util.Resource
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterFragmentViewModel @Inject constructor(
    private val rateRepository: RateRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    companion object {
        private val TAG = this::class.simpleName
    }

    sealed class CurrencyEvent {
        class Success(val resultText: String): CurrencyEvent()
        class Failure(val errorText: String): CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion = _conversion


    fun convert(amount: String, from: String, to: String) {
        val fromAmount = amount.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Invalid amount")
            return
        }
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyEvent.Loading
            val query = HashMap<String, String>()
            query["to"] = to
            query["from"] = from
            query["amount"] = amount
            when(val ratesResponse = rateRepository.getExchangeRate(query)) {
                is Resource.Error -> CurrencyEvent.Failure(ratesResponse.message!!)
                is Resource.Success -> {
                    val rate = ratesResponse.response?.result
                    if (rate == null) {
                        _conversion.value = CurrencyEvent.Failure("Unexpected Error")
                    } else {
                        _conversion.value = CurrencyEvent.Success("$amount $from = $rate $to")
                        Log.d(TAG, "convert: ${conversion.value}")
                    }
                }
            }
        }
    }

}