package asgarov.elchin.plantly.feature_reminder.data.remote.dto


data class ReminderDto(
    val id: Long? = null,
    val plantId: Long,
    val plantName: String,
    val reminderType: ReminderTypeDto,
    val repeatEvery: Int,
    val repeatUnit: String,
    val reminderTime: String,
    val previousData: PreviousDataDto
)