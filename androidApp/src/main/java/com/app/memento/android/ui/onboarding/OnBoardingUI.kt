package com.app.memento.android.ui.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.memento.android.R
import com.app.memento.android.ui.common.LargeMementoButton
import com.app.memento.android.ui.common.TransparentButton
import com.app.memento.android.ui.navigation.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnBoardingUI(navHostController: NavHostController, onBoardingViewModel: OnBoardingViewModel = hiltViewModel()) {
    val pages = listOf(
        OnBoardingPage.First, OnBoardingPage.Second, OnBoardingPage.Third, OnBoardingPage.Fourth
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TransparentButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 14.dp, end = 14.dp)
                .alpha(if (pagerState.currentPage < 3) 1f else 0f), onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(3)
                }
            }, contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Skip",
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
            )
        }

        HorizontalPager(
            modifier = Modifier.fillMaxHeight(0.8f), count = 4, state = pagerState
        ) { position ->
            OnBoardingPageScreen(onBoardingPage = pages[position])
        }

        if (pagerState.currentPage == 3) LargeMementoButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp, bottom = 10.dp, start = 14.dp, end = 14.dp)
                .height(60.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                onBoardingViewModel.saveOnBoardingState(true)
                navHostController.popBackStack()
                navHostController.navigate(Screen.SignInScreen.route)
            },
        ) {
            Text(text = "Get started", style = MaterialTheme.typography.bodyLarge)
        }

        if (pagerState.currentPage < 3) {
            val currentPercentage = animateFloatAsState(
                targetValue = (((pagerState.currentPage + 1) * 120f)),
                animationSpec = tween(durationMillis = 500, delayMillis = 0)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                val backgroundStrokeColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                val progressStrokeColor = MaterialTheme.colorScheme.primary
                val circleColor = MaterialTheme.colorScheme.primary
                Canvas(modifier = Modifier.size(80.dp)) {
                    drawArc(
                        color = backgroundStrokeColor,
                        -90f,
                        360f,
                        useCenter = false,
                        style = Stroke(4f, cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = progressStrokeColor,
                        -90f,
                        currentPercentage.value,
                        useCenter = false,
                        style = Stroke(6f, cap = StrokeCap.Round)
                    )
                }

                Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Canvas(modifier = Modifier.size(55.dp)) {
                        drawArc(
                            color = circleColor,
                            -90f,
                            360f,
                            useCenter = false,
                        )
                    }
                    Icon(
                        tint = MaterialTheme.colorScheme.onPrimary,
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = "On boarding forward"
                    )

                }
            }
        }
    }
}

@Composable
fun OnBoardingPageScreen(onBoardingPage: OnBoardingPage, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.fillMaxHeight(0.5f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = onBoardingPage.title
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = onBoardingPage.title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 20.dp),
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline),
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun OnBoardingUIPreview() {
    Surface(color = Color.White) {
        OnBoardingUI(navHostController = rememberNavController())
    }
}

@Preview
@Composable
fun FirstOnBoardingPageScreen() {
    Surface(color = Color.White) {
        OnBoardingPageScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Preview
@Composable
fun SecondOnBoardingPageScreen() {
    Surface(color = Color.White) {
        OnBoardingPageScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Preview
@Composable
fun ThirdOnBoardingPageScreen() {
    Surface(color = Color.White) {
        OnBoardingPageScreen(onBoardingPage = OnBoardingPage.Third)
    }
}

@Preview
@Composable
fun FourthOnBoardingPageScreen() {
    Surface(color = Color.White) {
        OnBoardingPageScreen(onBoardingPage = OnBoardingPage.Fourth)
    }
}