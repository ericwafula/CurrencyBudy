package tech.ericwathome.currencybudy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import tech.ericwathome.currencybudy.model.CurrencyEvent
import tech.ericwathome.currencybudy.repository.RateRepository
import tech.ericwathome.currencybudy.util.DispatcherProvider
import tech.ericwathome.currencybudy.util.Resource
import javax.inject.Inject

@HiltViewModel
class LatestFragmentViewModel @Inject constructor(
    private val rateRepository: RateRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    companion object {
        private val TAG = this::class.simpleName
    }

    private val _currencies = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val currencies = _currencies

    fun fetchPopularRates() {
        viewModelScope.launch(dispatchers.io) {
            _currencies.value = CurrencyEvent.Loading
            val query = HashMap<String, String>()
            query["symbols"] = "eur,gbp,jpy,cad,zar,rub,mxn"
            query["base"] = "usd"
            when (val ratesResponse = rateRepository.getPopularRates(query)) {
                is Resource.Error -> CurrencyEvent.Failure(ratesResponse.message!!)
                is Resource.Success -> {
                    val popularRates = ratesResponse.response?.rates
                    if (popularRates == null) {
                        _currencies.value = CurrencyEvent.Failure("Unexpected Error")
                    } else {
                        _currencies.value = CurrencyEvent.Success(rates = popularRates)
                    }
                }
            }
        }
    }

}