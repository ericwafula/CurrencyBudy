package tech.ericwathome.presentation.onboarding_screen.util

sealed class AuthUiEvent{

    data class IsEmailChangedSignIn(val email : String) : AuthUiEvent()
    data class IsPasswordChangedSignIn(val password : String) : AuthUiEvent()
    object SignIn : AuthUiEvent()

    data class IsNameChangedSignUp(val name : String) : AuthUiEvent()
    data class IsEmailChangedSignUp(val email : String) : AuthUiEvent()
    data class IsPasswordChangedSignUp(val password : String) : AuthUiEvent()
    object SignUp : AuthUiEvent()


}
