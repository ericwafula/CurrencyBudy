package com.ericwathome.currencybuddy.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.ericwathome.converter_presentation.converter_screen.ConverterScreen
import tech.ericwathome.presentation.Screens
import tech.ericwathome.presentation.onboarding_screen.OnboardingScreen
import tech.ericwathome.presentation.onboarding_screen.profile.ProfileScreen
import tech.ericwathome.presentation.onboarding_screen.signin.SignInScreen
import tech.ericwathome.presentation.onboarding_screen.signup.SignUpScreen

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination) {
        composable(route = Screens.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(route = Screens.SignIn.route){
            SignInScreen(
                onLogin = {
                    navController.navigate(Screens.Converter.route){
                        popUpTo(Screens.Converter.route){
                            inclusive = true
                        }
                    }
                },
                onSignUp = {
                    navController.navigate(Screens.SignUp.route)
                }
            )
        }
        composable(route = Screens.SignUp.route){
            SignUpScreen(
                onSignUp = {
                    navController.navigate(Screens.Converter.route){
                        popUpTo(Screens.Converter.route){
                            inclusive = true
                        }
                    }
                },
                onLogin = {
                    navController.navigate(Screens.SignIn.route)
                }
            )
        }
        composable(route = Screens.Converter.route) {
            ConverterScreen(
                onProfileClicked = {
                    navController.navigate(Screens.Profile.route)
                }
            )
        }
        composable(route = Screens.Profile.route){
            ProfileScreen(
                onLogOut = {
                           navController.navigate(Screens.SignIn.route)
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}