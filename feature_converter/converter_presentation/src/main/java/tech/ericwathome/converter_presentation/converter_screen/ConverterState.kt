package tech.ericwathome.converter_presentation.converter_screen

data class ConverterState(
    var data: ConverterValues? = null,
    var message: String = "",
    var loading: Boolean = false
)
