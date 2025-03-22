package asgarov.elchin.plantly.feature_reminder.data.mapper

import asgarov.elchin.plantly.feature_reminder.data.remote.dto.PreviousDataDto
import asgarov.elchin.plantly.feature_reminder.domain.model.PreviousData


fun PreviousDataDto.toPreviousData(): PreviousData {
    return PreviousData.valueOf(this.name)
}

fun PreviousData.toPreviousDataDto(): PreviousDataDto {
    return PreviousDataDto.valueOf(this.name)
}