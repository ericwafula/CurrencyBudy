package tech.ericwathome.onboarding_domain.auth

import com.google.firebase.auth.FirebaseUser
import tech.ericwathome.onboarding_domain.util.AuthResource

interface AuthRepository {

    /**
     *  this is just an example
     */
    suspend fun loginUserWithEmailAndPassword(email: String, password: String): AuthResource<FirebaseUser>

}