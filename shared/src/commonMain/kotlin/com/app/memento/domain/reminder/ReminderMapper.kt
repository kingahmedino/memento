package com.app.memento.domain.reminder

import database.ReminderEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun ReminderEntity.toReminder(): Reminder = Reminder(
    id = id,
    title = title,
    description = description,
    location = location,
    latitude = latitude,
    longitude = longitude,
    colorHex = colorHex,
    created = Instant.fromEpochMilliseconds(created)
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    triggered = triggered,
    pinned = pinned
)