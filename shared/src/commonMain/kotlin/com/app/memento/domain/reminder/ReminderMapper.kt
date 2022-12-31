package com.app.memento.domain.reminder

import com.app.memento.domain.time.DateTimeUtil
import database.ReminderEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Reminder.toReminderEntity(): ReminderEntity = ReminderEntity(
    id = id ?: 0,
    title = title,
    description = description,
    location = location,
    latitude = latitude,
    longitude = longitude,
    colorHex = colorHex,
    created = DateTimeUtil.toEpochMillis(created),
    triggered = triggered
)

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
    triggered = triggered
)