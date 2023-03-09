package tech.ericwathome.onboarding_domain.use_case

import javax.inject.Inject

data class OnboardingUseCases @Inject constructor (
    val getOnboardingStatus: GetOnboardingStatus,
    val updateOnboardingStatus: UpdateOnboardingStatus
)
