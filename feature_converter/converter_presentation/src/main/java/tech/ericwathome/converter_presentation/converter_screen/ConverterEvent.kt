package tech.ericwathome.converter_presentation.converter_screen

sealed class ConverterEvent {
    data class ShowDialog(val message: String) : ConverterEvent()
}