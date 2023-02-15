package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme

@Composable
fun OnboardingScreen() {

}

@Composable
fun BottomSection() {

}

@Composable
fun Indicators(index: Int) {
    val size = 3
    val index = 0
    
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun RowScope.Indicator(isSelected: Boolean = true) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondary
                }
            )
    ) {

    }
}

@Preview
@Composable
fun BottomSectionPreview() {
    CurrencyBuddyTheme {
        BottomSection()
    }
}

@Preview
@Composable
fun IndicatorsPreview() {
    CurrencyBuddyTheme {
        Indicators(3)
    }
}

@Preview
@Composable
fun Indicator() {
    CurrencyBuddyTheme {
        Row {
            Indicator()
        }
    }
}