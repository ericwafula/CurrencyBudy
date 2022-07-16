package tech.ericwathome.currencybudy.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.transition.Visibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import tech.ericwathome.currencybudy.databinding.FragmentCurrencyConverterBinding
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
        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.edtAmount.text.toString().trim(),
                binding.edtFrom.text.toString().trim(),
                binding.edtTo.text.toString().trim()
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.conversion.collect {
                when (it) {
                    is CurrencyConverterFragmentViewModel.CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.txvResult.setTextColor(Color.GREEN)
                        binding.txvResult.text = it.resultText
                    }
                    is CurrencyConverterFragmentViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.txvResult.setTextColor(Color.RED)
                        binding.txvResult.text = it.errorText
                    }
                    is CurrencyConverterFragmentViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }
}