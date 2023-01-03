package com.app.memento.android.ui.onboarding

import androidx.annotation.DrawableRes
import com.app.memento.android.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.onboarding1,
        title = "Be reminded, set reminders.",
        description = "Set reminders to go off when you arrive at or leave a specific location."
    )

    object Second: OnBoardingPage(
        image = R.drawable.onboarding2,
        title = "Get reminder on time",
        description = "Never forget to pick up the dry cleaning or grab a coffee on your way to work again!"
    )

    object Third: OnBoardingPage(
        image = R.drawable.onboarding3,
        title = "Getting started is easy",
        description = "Simply allow Memento to access your location, and start creating reminders by entering a task, choosing a location, and leave the rest to Memento."
    )

    object Fourth: OnBoardingPage(
        image = R.drawable.onboarding4,
        title = "Welcome to Memento! ",
        description = "Stay organized and on top of your tasks and appointments by setting reminders based on your location.\n"
    )
}
