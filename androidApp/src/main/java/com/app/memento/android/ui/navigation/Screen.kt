package com.app.memento.android.ui.navigation

sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen("onBoarding_screen")
    object SignInScreen : Screen("sign_in_screen")
    object HomeScreen : Screen("home_screen")
    object ChooseLocationScreen : Screen("choose_location_screen")
    object SearchLocationScreen : Screen("search_location_screen")
    object ReminderDetailsScreen : Screen("reminder_detail_screen")
    object ReminderCreatedScreen : Screen("reminder_created_screen")
    object ProfileScreen : Screen("profile_screen")
    object NotificationSettingsScreen : Screen("notification_settings_screen")
    object EditPersonalInfoScreen : Screen("edit_personal_info_screen")
}