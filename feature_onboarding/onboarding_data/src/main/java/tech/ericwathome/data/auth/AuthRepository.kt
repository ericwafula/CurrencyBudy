package tech.ericwathome.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import tech.ericwathome.onboarding_domain.auth.AuthService
import tech.ericwathome.onboarding_domain.util.FirebaseResource
import tech.ericwathome.onboarding_domain.util.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth : FirebaseAuth
) : AuthService {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override suspend fun signUp(
        email: String,
        name: String,
        password: String
    ): FirebaseResource<FirebaseUser> {
        return try {
            val result =  auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            return FirebaseResource.Success(result.user!!)
        } catch (e : Exception){
            e.printStackTrace()
            FirebaseResource.Failure(e)
        }
    }

    override suspend fun login(email: String, password: String): FirebaseResource<FirebaseUser> {
        return try {
            val result =  auth.signInWithEmailAndPassword(email, password).await()
            FirebaseResource.Success(result.user!!)
        } catch (e : Exception){
            e.printStackTrace()
            FirebaseResource.Failure(e)
        }
    }


    override fun logout() {
        auth.signOut()
    }
}