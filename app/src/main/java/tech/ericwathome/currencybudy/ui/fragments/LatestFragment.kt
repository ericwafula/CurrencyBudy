package tech.ericwathome.currencybudy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import tech.ericwathome.currencybudy.databinding.FragmentLatestBinding
import tech.ericwathome.currencybudy.model.CurrencyEvent
import tech.ericwathome.currencybudy.ui.viewmodels.LatestFragmentViewModel

@AndroidEntryPoint
class LatestFragment : Fragment() {
    private lateinit var binding: FragmentLatestBinding
    private val viewModel: LatestFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestBinding.inflate(inflater, container, false)
        initializeViews()
        return binding.root
    }

    private fun initializeViews() {
        binding.progressBar2.isVisible = true
        fetchPopularRates()

        lifecycleScope.launchWhenCreated {
            viewModel.currencies.collect {
                when (it) {
                    is CurrencyEvent.Success -> {
                        binding.progressBar2.isVisible = false
                        binding.txvEurUsdRate.text = it.rates?.eur
                        binding.txvGbpUsdRate.text = it.rates?.gbp
                        binding.txvUsdCadRate.text = it.rates?.cad
                        binding.txvUsdJpyRate.text = it.rates?.jpy
                        binding.txvUsdZarRate.text = "USD/ZAR = ${it.rates?.zar}"
                        binding.txvUsdMxnRate.text = "USD/MXN = ${it.rates?.mxn}"
                        binding.txvUsdRubRate.text = "USD/RUB = ${it.rates?.rub}"
                    }
                    is CurrencyEvent.Failure -> {
                        binding.progressBar2.isVisible = false
                    }
                    is CurrencyEvent.Loading -> {
                        binding.progressBar2.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun fetchPopularRates() {
        viewModel.fetchPopularRates()
    }
}