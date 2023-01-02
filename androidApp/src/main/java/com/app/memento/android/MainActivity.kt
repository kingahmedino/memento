package com.app.memento.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.app.memento.android.ui.navigation.Navigation
import com.app.memento.android.ui.splash.SplashViewModel
import com.app.memento.android.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                splashViewModel.isLoading.value
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    val destination by splashViewModel.startDestination
                    val navController = rememberNavController()
                    Navigation(navController = navController, startDestination = destination)
                }
            }
        }
    }
}
