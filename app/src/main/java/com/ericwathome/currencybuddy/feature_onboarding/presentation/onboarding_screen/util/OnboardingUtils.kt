package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal data class OnBoardingItem(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)

