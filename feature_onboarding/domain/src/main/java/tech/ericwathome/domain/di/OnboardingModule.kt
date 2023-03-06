package tech.ericwathome.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.ericwathome.data.preference.AppPreferences
import tech.ericwathome.domain.use_case.GetOnboardingStatus
import tech.ericwathome.domain.use_case.OnboardingUseCases
import tech.ericwathome.domain.use_case.UpdateOnboardingStatus

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {

    @Provides
    fun provideGetOnboardingStatusUseCase(preferences: AppPreferences) =
        GetOnboardingStatus(preferences)

    @Provides
    fun provideUpdateOnboardingStatus(preferences: AppPreferences) =
        UpdateOnboardingStatus(preferences)

    @Provides
    fun provideOnboardingUseCases(
        getOnboardingStatus: GetOnboardingStatus,
        updateOnboardingStatus: UpdateOnboardingStatus
    ): OnboardingUseCases {
        return OnboardingUseCases(
            getOnboardingStatus = getOnboardingStatus,
            updateOnboardingStatus = updateOnboardingStatus
        )
    }

}