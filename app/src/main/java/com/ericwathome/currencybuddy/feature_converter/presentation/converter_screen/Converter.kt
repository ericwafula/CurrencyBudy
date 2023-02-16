package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme

@Composable
fun ConverterScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Converter Screen")
    }
}

@Preview
@Composable
fun ConverterScreenPreview() {
    CurrencyBuddyTheme {
        ConverterScreen()
    }
}