package tech.ericwathome.onboarding_domain.util

sealed class FirebaseResource<out R>{
    data class Success<out R>(val result: R) : FirebaseResource<R>()
    data class Failure(val exception: Exception) : FirebaseResource<Nothing>()
    object Loading : FirebaseResource<Nothing>()
}