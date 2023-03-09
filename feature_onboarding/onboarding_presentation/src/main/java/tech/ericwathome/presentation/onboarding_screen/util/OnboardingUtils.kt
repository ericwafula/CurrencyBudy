package tech.ericwathome.presentation.onboarding_screen.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import tech.ericwathome.presentation.R

data class Item(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)

internal object OnboardingUtils {
    val items = listOf(
        Item(
            image = tech.ericwathome.core_presentation.R.drawable.onboarding_image_1,
            text = R.string.onboarding_text_1
        ),
        Item(
            image = tech.ericwathome.core_presentation.R.drawable.onboarding_image_2,
            text = R.string.onboarding_text_2
        ),
        Item(
            image = tech.ericwathome.core_presentation.R.drawable.onboarding_image_3,
            text = R.string.onboarding_text_3
        )
    )
}

