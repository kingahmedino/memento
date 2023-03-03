package com.app.memento.android.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.memento.android.utils.DataStoreUtils
import com.app.memento.domain.reminder.Reminder
import com.app.memento.domain.reminder.ReminderDAO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataStoreUtils: DataStoreUtils, private val reminderDAO: ReminderDAO): ViewModel() {
    private val _triggeredReminders = MutableStateFlow(listOf<Reminder>())
    val triggeredReminders = _triggeredReminders.asStateFlow()
    private val nonUITriggeredReminders = mutableListOf<Reminder>()

    private val _notYetTriggeredReminders = MutableStateFlow(listOf<Reminder>())
    val notYetTriggeredReminders = _notYetTriggeredReminders.asStateFlow()
    private val nonUINonTriggeredReminders = mutableListOf<Reminder>()

    var firstName = mutableStateOf("")
        private set

    init {
        getReminders()
        getUsername()
    }

    private fun getUsername() {
        viewModelScope.launch {
            dataStoreUtils.readUsername().first { username ->
                if (username.isNotEmpty())
                    firstName.value = username.split(" ")[0]
                true
            }
        }
    }

    private fun getTriggeredReminders() {
        viewModelScope.launch {
            val reminders = reminderDAO.getAllTriggeredReminders()
            nonUITriggeredReminders.clear()
            nonUITriggeredReminders.addAll(reminders)
            _triggeredReminders.emit(reminders)
        }
    }

    private fun getNotYetTriggeredReminders() {
        viewModelScope.launch {
            val reminders = reminderDAO.getAllNonTriggeredReminders()
            nonUINonTriggeredReminders.clear()
            nonUINonTriggeredReminders.addAll(reminders)
            _notYetTriggeredReminders.emit(reminders)
        }
    }

    private fun getReminders() {
        getTriggeredReminders()
        getNotYetTriggeredReminders()
    }

    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderDAO.deleteReminder(reminder.id!!)
            getReminders()
        }
    }

    fun pinOrUnpinReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderDAO.insertReminder(reminder.copy(pinned = !reminder.pinned))
            getReminders()
        }
    }

    fun searchReminder(query: String) = viewModelScope.launch {
        val triggeredReminderResult = nonUITriggeredReminders.filter {
            it.title.contains(query) || it.description.contains(query) || it.location.contains(query)
        }
        val nonTriggeredReminderResult = nonUINonTriggeredReminders.filter {
            it.title.contains(query) || it.description.contains(query) || it.location.contains(query)
        }
        _triggeredReminders.emit(triggeredReminderResult)
        _notYetTriggeredReminders.emit(nonTriggeredReminderResult)
    }
}