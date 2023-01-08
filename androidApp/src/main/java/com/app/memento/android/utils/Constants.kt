package com.app.memento.android.utils

import com.app.memento.domain.reminder.Reminder
import com.app.memento.domain.time.DateTimeUtil

object Constants {
    const val LOCATION_TRACKING_NOTIFICATION_CHANNEL_ID = "location"
    const val LOCATION_TRACKING_NOTIFICATION_CHANNEL_NAME = "Location"
    const val LOCATION_TRACKING_FOREGROUND_ID = 1

    val SAMPLE_TRIGGERED_REMINDERS = listOf(
        Reminder(
            id = 1,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFFFFBE5,
            created = DateTimeUtil.now(),
            triggered = true
        ),
        Reminder(
            id = 2,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFEEFEE7,
            created = DateTimeUtil.now(),
            triggered = true
        ),
        Reminder(
            id = 3,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFFFFBE5,
            created = DateTimeUtil.now(),
            triggered = true
        ),
        Reminder(
            id = 4,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFEEFEE7,
            created = DateTimeUtil.now(),
            triggered = true
        ),
        Reminder(
            id = 5,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFFFFBE5,
            created = DateTimeUtil.now(),
            triggered = true
        ),
        Reminder(
            id = 6,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFEEFEE7,
            created = DateTimeUtil.now(),
            triggered = true
        )
    )

    val SAMPLE_NOT_YET_TRIGGERED_REMINDERS = listOf(
        Reminder(
            id = 1,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFFFFBE5,
            created = DateTimeUtil.now(),
            triggered = false
        ),
        Reminder(
            id = 2,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFEEFEE7,
            created = DateTimeUtil.now(),
            triggered = false
        ),
        Reminder(
            id = 3,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFFFFBE5,
            created = DateTimeUtil.now(),
            triggered = false
        ),
        Reminder(
            id = 4,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFEEFEE7,
            created = DateTimeUtil.now(),
            triggered = false
        ),
        Reminder(
            id = 5,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFFFFBE5,
            created = DateTimeUtil.now(),
            triggered = false
        ),
        Reminder(
            id = 6,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFEEFEE7,
            created = DateTimeUtil.now(),
            triggered = false
        ),
        Reminder(
            id = 7,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulabal Corn agbado and everything you need to enjoy life when it finally dawns on you.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFFFFBE5,
            created = DateTimeUtil.now(),
            triggered = false
        ),
        Reminder(
            id = 8,
            title = "Buy water",
            description = "I need to get water on my way back from work. Bala blu bulaba.",
            location = "Sanrab, Tanke, Oke-Odo",
            latitude = 8.32333,
            longitude = 4.54343,
            colorHex = 0xFFEEFEE7,
            created = DateTimeUtil.now(),
            triggered = false
        )
    )
}