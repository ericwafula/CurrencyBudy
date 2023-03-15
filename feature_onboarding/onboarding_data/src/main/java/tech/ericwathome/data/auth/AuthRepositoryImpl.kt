package tech.ericwathome.data.auth

import com.google.firebase.auth.FirebaseUser
import tech.ericwathome.onboarding_domain.auth.AuthRepository
import tech.ericwathome.onboarding_domain.util.AuthResource

class AuthRepositoryImpl : AuthRepository {
    override suspend fun loginUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResource<FirebaseUser> {
        TODO("Not yet implemented")
    }
}