package com.app.memento.domain.reminder

import com.app.memento.database.MementoDb

class ReminderDAOImpl(db: MementoDb): ReminderDAO {
    private val queries = db.reminderQueries

    override suspend fun insertReminder(reminder: Reminder) {
        queries.insertReminder(reminder.toReminderEntity())
    }

    override suspend fun getReminderById(id: Long): Reminder? {
        return queries.getReminderById(id).executeAsOneOrNull()?.toReminder()
    }

    override suspend fun getAllReminders(): List<Reminder> {
        return queries.getAllReminders().executeAsList().map { it.toReminder() }.reversed()
    }

    override suspend fun getAllTriggeredNotes(): List<Reminder> {
        return queries.getAllReminders().executeAsList().map { it.toReminder() }.reversed()
    }

    override suspend fun getAllNonTriggeredNotes(): List<Reminder> {
        return queries.getAllReminders().executeAsList().map { it.toReminder() }.reversed()
    }

    override suspend fun deleteReminder(id: Long) {
        queries.deleteReminder(id)
    }

}