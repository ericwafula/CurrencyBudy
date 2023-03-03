package com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen

sealed class ConverterEvent {
    data class ShowDialog(val message: String) : ConverterEvent()
}