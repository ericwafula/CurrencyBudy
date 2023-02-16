package com.ericwathome.currencybuddy.ui

sealed class Screens(val route: String) {
    object Onboarding : Screens("onboarding")
    object Converter : Screens("converter")
}
