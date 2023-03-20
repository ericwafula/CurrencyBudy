package tech.ericwathome.presentation

sealed class Screens(val route: String) {
    object Onboarding : Screens(ONBOARDING_ROUTE)
    object Converter : Screens(CONVERTER_ROUTE)

    object SignIn : Screens(SIGN_IN_ROUTE)

    object SignUp : Screens(SIGN_UP_ROUTE)

    companion object {
        private const val ONBOARDING_ROUTE = "onboarding"
        private const val CONVERTER_ROUTE = "converter"
        private const val SIGN_IN_ROUTE = "signin"
        private const val SIGN_UP_ROUTE = "signup"

    }
}
