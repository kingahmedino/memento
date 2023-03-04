package com.app.memento.domain.reminder

import com.app.memento.database.MementoDb
import com.app.memento.domain.time.DateTimeUtil

class ReminderDAOImpl(db: MementoDb): ReminderDAO {
    private val queries = db.reminderQueries

    override suspend fun insertReminder(reminder: Reminder) {
        queries.insertReminder(
            id = reminder.id,
            title = reminder.title,
            description = reminder.description,
            location = reminder.location,
            latitude = reminder.latitude,
            longitude = reminder.longitude,
            colorHex = reminder.colorHex,
            created = DateTimeUtil.toEpochMillis(reminder.created),
            triggered = reminder.triggered,
            pinned = reminder.pinned
        )
    }

    override suspend fun getReminderById(id: Long): Reminder? {
        return queries.getReminderById(id).executeAsOneOrNull()?.toReminder()
    }

    override suspend fun getAllReminders(): List<Reminder> {
        return queries.getAllReminders().executeAsList().map { it.toReminder() }.reversed()
    }

    override suspend fun getAllTriggeredReminders(): List<Reminder> {
        return queries.getAllTriggeredReminders().executeAsList().map { it.toReminder() }.reversed()
    }

    override suspend fun getAllNonTriggeredReminders(): List<Reminder> {
        return queries.getAllNonTriggeredReminders().executeAsList().map { it.toReminder() }.reversed()
    }

    override suspend fun deleteReminder(id: Long) {
        queries.deleteReminder(id)
    }

}