package tech.ericwathome.presentation.navigation

import androidx.navigation.NavHostController

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