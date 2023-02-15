package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.feature_converter.presentation.converter_screen.theme.CurrencyBuddyTheme
import com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.util.Item
import com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.util.OnboardingUtils
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.spacedBy(60.dp),
        modifier = Modifier.padding(bottom = 48.dp)
    ) {
        HorizontalPager(
            count = OnboardingUtils.items.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) { page ->
            OnboardingItem(item = OnboardingUtils.items[page])
        }
        BottomSection(size = OnboardingUtils.items.size, index = pagerState.currentPage) {
            scope.launch {
                if (pagerState.currentPage + 1 < OnboardingUtils.items.size) {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else {
                    /**
                     * navigate to converter screen
                     */
                }
            }
        }
    }
}

@Composable
fun OnboardingItem(
    item: Item
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(
            bottomStart = 40.dp,
            bottomEnd = 40.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = stringResource(
                    id = R.string.image_description
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(id = item.text),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
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
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.primary
                }
            )
    ) {

    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    CurrencyBuddyTheme {
        OnboardingScreen()
    }
}

@Preview
@Composable
fun OnboardingItemPreview() {
    CurrencyBuddyTheme {
        OnboardingItem(
            item = OnboardingUtils.items[0]
        )
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