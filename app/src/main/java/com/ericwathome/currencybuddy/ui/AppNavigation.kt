package com.ericwathome.currencybuddy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.ConverterScreen
import com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.OnboardingScreen

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

fun NavHostController.navigatePopUpTo(route: String) {
    this.navigate(route) {
        popUpTo(
            this@navigatePopUpTo.graph.startDestinationId
        ) {
            saveState = true
            inclusive = false
        }
        launchSingleTop = true
        restoreState = true
    }
}