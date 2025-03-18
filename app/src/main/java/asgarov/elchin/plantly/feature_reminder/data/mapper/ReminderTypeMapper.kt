package asgarov.elchin.plantly.feature_reminder.data.mapper

import asgarov.elchin.plantly.feature_reminder.data.remote.dto.ReminderTypeDto
import asgarov.elchin.plantly.feature_reminder.domain.model.ReminderType

fun ReminderTypeDto.toReminderType(): ReminderType {
    return ReminderType.valueOf(this.name)
}

fun ReminderType.toReminderTypeDto(): ReminderTypeDto {
    return ReminderTypeDto.valueOf(this.name)
}