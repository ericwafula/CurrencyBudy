package tech.ericwathome.data.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.data.auth.AuthRepository
import tech.ericwathome.onboarding_domain.auth.AuthService

@Module
@InstallIn(SingletonComponent::class)
object FirebaseAuthModule {

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(service: AuthRepository) : AuthService = service

}