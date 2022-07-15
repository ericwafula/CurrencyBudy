package tech.ericwathome.currencybudy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.ericwathome.currencybudy.databinding.FragmentCurrencyConverterBinding

class CurrencyConverterFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyConverterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyConverterBinding.inflate(inflater, container, false)
        return binding.root
    }
}