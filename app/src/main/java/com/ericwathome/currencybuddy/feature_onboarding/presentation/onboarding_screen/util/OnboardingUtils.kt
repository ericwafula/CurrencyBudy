package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ericwathome.currencybuddy.R

internal data class OnBoardingItem(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)

internal object OnboardingUtils {
    val onBoardingItems = listOf(
        OnBoardingItem(
            image = R.drawable.onboarding_image_1,
            text = R.string.onboarding_text_1
        ),
        OnBoardingItem(
            image = R.drawable.onboarding_image_2,
            text = R.string.onboarding_text_2
        ),
        OnBoardingItem(
            image = R.drawable.onboarding_image_3,
            text = R.string.onboarding_text_3
        )
    )
}

