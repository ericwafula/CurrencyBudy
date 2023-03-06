package com.ericwathome.currencybuddy.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.ConverterScreen
import tech.ericwathome.presentation.Screens
import tech.ericwathome.presentation.onboarding_screen.OnboardingScreen

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination) {
        composable(route = Screens.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(route = Screens.Converter.route) {
            ConverterScreen()
        }
    }
}