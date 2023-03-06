package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ericwathome.currencybuddy.R
import tech.ericwathome.presentation.onboarding_screen.util.Item

data class Item(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)

internal object OnboardingUtils {
    val items = listOf(
        Item(
            image = R.drawable.onboarding_image_1,
            text = R.string.onboarding_text_1
        ),
        Item(
            image = R.drawable.onboarding_image_2,
            text = R.string.onboarding_text_2
        ),
        Item(
            image = R.drawable.onboarding_image_3,
            text = R.string.onboarding_text_3
        )
    )
}

