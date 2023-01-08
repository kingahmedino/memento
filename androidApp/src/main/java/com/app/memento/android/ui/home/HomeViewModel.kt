package com.app.memento.android.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.memento.android.utils.DataStoreUtils
import com.app.memento.domain.reminder.Reminder
import com.app.memento.domain.reminder.ReminderDAO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataStoreUtils: DataStoreUtils, private val reminderDAO: ReminderDAO): ViewModel() {
    private val _triggeredReminders = MutableStateFlow(listOf<Reminder>())
    val triggeredReminders: StateFlow<List<Reminder>> get() = _triggeredReminders

    private val _notYetTriggeredReminders = MutableStateFlow(listOf<Reminder>())
    val notYetTriggeredReminders: StateFlow<List<Reminder>> get() = _notYetTriggeredReminders

    private val _revealedReminders = MutableStateFlow(listOf<Reminder>())
    val revealedReminders: StateFlow<List<Reminder>> get() = _revealedReminders

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
            println(reminders)
            _triggeredReminders.emit(reminders)
        }
    }

    private fun getNotYetTriggeredReminders() {
        viewModelScope.launch {
            val reminders = reminderDAO.getAllNonTriggeredReminders()
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

    fun onItemExpanded(reminder: Reminder) {
        if (_revealedReminders.value.contains(reminder)) return
        _revealedReminders.value = _revealedReminders.value.toMutableList().also { list ->
            list.add(reminder)
        }
    }

    fun onItemCollapsed(reminder: Reminder) {
        if (!_revealedReminders.value.contains(reminder)) return
        _revealedReminders.value = _revealedReminders.value.toMutableList().also { list ->
            list.remove(reminder)
        }
    }
}