package com.app.memento.domain.reminder

interface ReminderDAO {
    suspend fun insertReminder(reminder: Reminder)
    suspend fun getReminderById(id: Long): Reminder?
    suspend fun getAllReminders(): List<Reminder>
    suspend fun getAllTriggeredNotes(): List<Reminder>
    suspend fun getAllNonTriggeredNotes(): List<Reminder>
    suspend fun deleteReminder(id: Long)
}