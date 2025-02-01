package se.braindome.urkraft.ui.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.braindome.urkraft.ui.onboarding.components.PageIndicator

@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(0, 0F, { 4 })
    val coroutineScope = rememberCoroutineScope()
    var text = remember {""}
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )
    Box(modifier = Modifier.fillMaxHeight()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize(), flingBehavior = fling) { page ->
            AnimatedVisibility(
                visible = pagerState.currentPage == page,
                enter = fadeIn(animationSpec = tween()),
                exit = fadeOut(animationSpec = tween()),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        when (pagerState.currentPage) {
                            0 -> {
                                ProgramSelectionPage(pagerState, coroutineScope)
                            }
                            1 -> {
                                ProgramPropertyPage()
                            }
                            2 -> {
                                ProgramPlanningPage()
                            }
                            3 -> {
                                OnboardingEndPage()
                            }
                        }
                        Text(text = text)
                    }
                }
            }

        }

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                repeat(4) {
                    PageIndicator(isSelected= pagerState.currentPage == it)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF555555)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}



