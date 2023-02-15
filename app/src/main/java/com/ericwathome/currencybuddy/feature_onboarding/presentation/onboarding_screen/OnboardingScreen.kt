package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState()

    Column {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) { page ->

        }
        BottomSection(size = 3, index = pagerState.currentPage) {

        }
    }
}

@Composable
fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        Indicators(size = size, index = index)
        FloatingActionButton(onClick = {
            onNextClicked()
        }) {
            if (index < 2) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward_24),
                    contentDescription = null
                )
            } else {
                Text(text = "Get Started", modifier = Modifier.padding(horizontal = 24.dp))
            }
        }
    }
}

@Composable
fun Indicators(size: Int, index: Int) {
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
        BottomSection(
            size = 3,
            index = 0,
            onNextClicked = { }
        )
    }
}