package asgarov.elchin.plantly.feature_reminder.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReminderDto(
    val id: Long? = null,
    val plantId: Long,
    val plantName: String,
    val reminderType: ReminderTypeDto,
    val repeatEvery: Int,
    val repeatUnit: String,
    val reminderTime: String,
    val previousData: PreviousDataDto,
    val nextReminderDateTime: String,
)