package tech.ericwathome.currencybudy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.ericwathome.currencybudy.databinding.FragmentLatestBinding

class LatestFragment : Fragment() {
    private lateinit var binding: FragmentLatestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestBinding.inflate(inflater, container, false)
        return binding.root
    }
}