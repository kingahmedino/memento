package com.app.memento.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.app.memento.android.ui.navigation.Navigation
import com.app.memento.android.ui.splash.SplashViewModel
import com.app.memento.android.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                splashViewModel.startDestination.value.isEmpty()
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    if (splashViewModel.startDestination.value.isNotEmpty()) {
                        val navController = rememberNavController()
                        Navigation(navController = navController, startDestination = splashViewModel.startDestination.value)
                    }
                }
            }
        }
    }
}
