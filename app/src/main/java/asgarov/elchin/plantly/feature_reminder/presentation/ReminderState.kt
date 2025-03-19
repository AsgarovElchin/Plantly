package asgarov.elchin.plantly.feature_reminder.presentation

import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder

data class ReminderState(
    val reminder: Reminder? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
