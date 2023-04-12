package tech.ericwathome.presentation.onboarding_screen.util

data class AuthState(
    val emailSignIn : String = "",
    val passwordSignIn : String = "",

    val nameSignUp : String = "",
    val emailSignUp : String = "",
    val passwordSignUp : String = "",

    val isLoading : Boolean = false
)
