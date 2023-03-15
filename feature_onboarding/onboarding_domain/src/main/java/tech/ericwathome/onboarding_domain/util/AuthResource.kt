package tech.ericwathome.onboarding_domain.util

sealed class AuthResource<out T> {

    class Success<T>(val data: T) : AuthResource<T>()

    class Failure<T>(val exception: Exception) : AuthResource<T>()

    class Loading<T> : AuthResource<T>()

}
