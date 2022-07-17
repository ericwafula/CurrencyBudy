package tech.ericwathome.currencybudy.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.transition.Visibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import tech.ericwathome.currencybudy.R
import tech.ericwathome.currencybudy.databinding.FragmentCurrencyConverterBinding
import tech.ericwathome.currencybudy.model.CurrencyEvent
import tech.ericwathome.currencybudy.ui.viewmodels.CurrencyConverterFragmentViewModel

@AndroidEntryPoint
class CurrencyConverterFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyConverterBinding

    private val viewModel: CurrencyConverterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyConverterBinding.inflate(inflater, container, false)
        initializeViews()
        return binding.root
    }

    private fun initializeViews() {
        binding.progressBar.isVisible = false
        val currencyAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.currencies, android.R.layout.simple_spinner_item)
        binding.spinnerFrom.apply {
            adapter = currencyAdapter
            setSelection(1)
        }
        binding.spinnerTo.apply {
            adapter = currencyAdapter
            setSelection(0)
        }

        convert()

        binding.btnConvert.setOnClickListener {
            binding.txvConvertedCurrency.text = "${binding.spinnerFrom.selectedItem}/${binding.spinnerTo.selectedItem}"
            convert()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.conversion.collect {
                when (it) {
                    is CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.txvConvertedResult.text = it.resultText
                    }
                    is CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                    }
                    is CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun convert() {
        viewModel.convert(
            binding.edtAmount.text.toString().trim(),
            binding.spinnerFrom.selectedItem.toString(),
            binding.spinnerTo.selectedItem.toString()
        )
    }
}