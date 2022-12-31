package com.app.memento.domain.reminder

import kotlinx.datetime.LocalDateTime

data class Reminder(
    val id: Long?,
    val title: String,
    val description: String,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val colorHex: Long,
    val created: LocalDateTime,
    val triggered: Boolean
)