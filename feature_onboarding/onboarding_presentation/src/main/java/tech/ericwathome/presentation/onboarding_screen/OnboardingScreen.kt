package tech.ericwathome.presentation.onboarding_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import tech.ericwathome.presentation.onboarding_screen.util.Item
import tech.ericwathome.presentation.onboarding_screen.util.OnboardingUtils
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import tech.ericwathome.presentation.R
import tech.ericwathome.presentation.Screens
import tech.ericwathome.presentation.navigation.navigatePopUpTo
import tech.ericwathome.presentation.theme.CurrencyBuddyTheme
import tech.ericwathome.presentation.util.Padding
import tech.ericwathome.presentation.util.Sizing
import tech.ericwathome.presentation.util.Spacing

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val viewModel: OnboardingViewModel = viewModel()

    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.dp_60),
        modifier = Modifier
            .padding()
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
                .padding(horizontal = Padding.dp_24),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = stringResource(
                    id = R.string.image_description
                )
            )
            Spacer(modifier = Modifier.height(Spacing.dp_60))
            Text(
                text = stringResource(id = item.text),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = Padding.dp_32)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = Padding.dp_32)
        ) {
            item {
                Indicators(size = size, index = index)
            }
            item {
                FloatingActionButton(
                    onClick = {
                        onNextClicked()
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    AnimatedContent(
                        targetState = index < 2,
                        transitionSpec = { fadeIn(
                            animationSpec = tween(
                                durationMillis = 300
                            )
                        ) with fadeOut(
                            animationSpec = tween(
                                durationMillis = 300
                            )
                        ) }
                    ) { showIcon ->
                        if (showIcon) {
                            Icon(
                                painter = painterResource(id = tech.ericwathome.core_presentation.R.drawable.ic_arrow_forward_24),
                                contentDescription = stringResource(id = R.string.forward_icon)
                            )
                        } else {
                            Text(
                                text = stringResource(id = R.string.get_started),
                                modifier = Modifier.padding(horizontal = Padding.dp_24)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Padding.dp_12)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun RowScope.Indicator(isSelected: Boolean = true) {
    val width by animateDpAsState(
        targetValue = if (isSelected) Padding.dp_24 else Padding.dp_12,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = Modifier
            .height(Sizing.dp_10)
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