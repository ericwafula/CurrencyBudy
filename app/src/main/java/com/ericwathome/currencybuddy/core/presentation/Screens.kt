package com.ericwathome.currencybuddy.core.presentation

sealed class Screens(val route: String) {
    object Onboarding : Screens(ONBOARDING_ROUTE)
    object Converter : Screens(CONVERTER_ROUTE)

    companion object {
        private const val ONBOARDING_ROUTE = "onboarding"
        private const val CONVERTER_ROUTE = "converter"
    }
}
