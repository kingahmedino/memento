package com.app.memento.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.memento.android.ui.onboarding.OnBoardingUI
import com.app.memento.android.ui.onboarding.SignInUI
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Navigation(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.OnBoardingScreen.route) {
            OnBoardingUI(navHostController = navController)
        }
        composable(route = Screen.SignInScreen.route) {
            SignInUI(navHostController = navController)
        }
        composable(route = Screen.HomeScreen.route) {}
        composable(route = Screen.ChooseLocationScreen.route) {}
        composable(route = Screen.SearchLocationScreen.route) {}
        composable(route = Screen.ReminderDetailsScreen.route) {}
        composable(route = Screen.ReminderCreatedScreen.route) {}
        composable(route = Screen.ProfileScreen.route) {}
        composable(route = Screen.NotificationSettingsScreen.route) {}
        composable(route = Screen.EditPersonalInfoScreen.route) {}
    }
}