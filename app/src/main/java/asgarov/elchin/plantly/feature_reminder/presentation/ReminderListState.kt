package asgarov.elchin.plantly.feature_reminder.presentation

import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder

data class ReminderListState(
    val reminders: List<Reminder>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)