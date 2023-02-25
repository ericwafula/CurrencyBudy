package com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ericwathome.currencybuddy.R
import com.ericwathome.currencybuddy.common.util.Padding
import com.ericwathome.currencybuddy.common.util.Sizing
import com.ericwathome.currencybuddy.common.util.Spacing
import com.ericwathome.currencybuddy.ui.theme.CurrencyBuddyTheme
import com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.util.Item
import com.ericwathome.currencybuddy.feature_onboarding.presentation.onboarding_screen.util.OnboardingUtils
import com.ericwathome.currencybuddy.ui.Screens
import com.ericwathome.currencybuddy.ui.navigatePopUpTo
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val viewModel: OnboardingViewModel = hiltViewModel()

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.p_60),
        modifier = Modifier.padding()
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        HorizontalPager(
            count = OnboardingUtils.items.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { page ->
            OnboardingItem(item = OnboardingUtils.items[page])
        }
        BottomSection(size = OnboardingUtils.items.size, index = pagerState.currentPage) {
            scope.launch {
                if (pagerState.currentPage < OnboardingUtils.items.lastIndex) {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else {
                    viewModel.updateOnboardingState(true)
                    navController.navigatePopUpTo(Screens.Converter.route)
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
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Padding.p_24),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = stringResource(
                    id = R.string.image_description
                )
            )
            Spacer(modifier = Modifier.height(Spacing.p_60))
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
    Surface(color = MaterialTheme.colorScheme.background) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(all = Padding.p_32)
                .fillMaxWidth(),
        ) {
            Indicators(size = size, index = index)
            FloatingActionButton(onClick = {
                onNextClicked()
            }, containerColor = MaterialTheme.colorScheme.primary) {
                if (index < 2) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward_24),
                        contentDescription = stringResource(id = R.string.forward_icon)
                    )
                } else {
                    Text(text = stringResource(id = R.string.get_started), modifier = Modifier.padding(horizontal = Padding.p_24))
                }
            }
        }
    }
}

@Composable
fun Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Padding.p_12)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun RowScope.Indicator(isSelected: Boolean = true) {
    val width by animateDpAsState(
        targetValue = if (isSelected) Padding.p_24 else Padding.p_12,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = Modifier
            .height(Sizing.p_10)
            .width(width)
            .clip(CircleShape)
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            )
    ) {

    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnboardingScreenPreview() {
    val context = LocalContext.current
    CurrencyBuddyTheme {
        OnboardingScreen(navController = NavHostController(context))
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnboardingItemPreview() {
    CurrencyBuddyTheme {
        OnboardingItem(
            item = OnboardingUtils.items[0]
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
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