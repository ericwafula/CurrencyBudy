package com.ericwathome.currencybuddy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.ericwathome.presentation.theme.CurrencyBuddyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            !viewModel.isLoading.value
        }
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyBuddyTheme {
                val navController = rememberNavController()
                val screen by viewModel.startDestination
                AppNavigation(navController = navController, startDestination = screen)
            }
        }
    }
}