package asgarov.elchin.plantly.feature_reminder.domain.model

import java.time.LocalDateTime

data class Reminder(
    val id: Long,
    val plantId: Long,
    val plantName: String,
    val reminderType: ReminderType,
    val repeatEvery: Int,
    val repeatUnit: String,
    val reminderTime: LocalDateTime,
    val previousData: PreviousData
)
