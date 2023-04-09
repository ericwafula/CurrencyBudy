package tech.ericwathome.onboarding_domain.auth

import com.google.firebase.auth.FirebaseUser
import tech.ericwathome.onboarding_domain.util.FirebaseResource

interface AuthService {

    val currentUser : FirebaseUser?
    suspend fun signUp(email : String, name : String, password : String) : FirebaseResource<FirebaseUser>
    suspend fun login(email : String, password : String) : FirebaseResource<FirebaseUser>
    fun logout()
}