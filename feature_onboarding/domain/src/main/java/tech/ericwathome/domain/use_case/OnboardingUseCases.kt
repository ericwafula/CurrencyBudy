package tech.ericwathome.domain.use_case

data class OnboardingUseCases(
    val getOnboardingStatus: GetOnboardingStatus,
    val updateOnboardingStatus: UpdateOnboardingStatus
)
