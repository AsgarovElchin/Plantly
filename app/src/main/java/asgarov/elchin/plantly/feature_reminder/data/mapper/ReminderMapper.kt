package asgarov.elchin.plantly.feature_reminder.data.mapper

import asgarov.elchin.plantly.feature_reminder.data.remote.dto.ReminderDto
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import java.time.LocalDateTime

fun ReminderDto.toReminder(): Reminder {
    return Reminder(
        id = id ?: 0L,
        plantId = plantId?: 0,
        plantName = plantName,
        reminderType = reminderType.toReminderType(),
        repeatEvery = repeatEvery,
        repeatUnit = repeatUnit,
        reminderTime = LocalDateTime.parse(reminderTime),
        previousData = previousData.toPreviousData(),
        nextReminderDateTime = LocalDateTime.parse(nextReminderDateTime)
    )
}

fun Reminder.toReminderDto(): ReminderDto {
    return ReminderDto(
        id = null,
        plantId = plantId,
        plantName = plantName,
        reminderType = reminderType.toReminderTypeDto(),
        repeatEvery = repeatEvery,
        repeatUnit = repeatUnit,
        reminderTime = reminderTime.toString(),
        previousData = previousData.toPreviousDataDto(),
        nextReminderDateTime = nextReminderDateTime.toString()
    )
}
