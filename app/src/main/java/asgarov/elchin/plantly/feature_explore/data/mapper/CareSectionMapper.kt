package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.CareSectionDto
import asgarov.elchin.plantly.feature_explore.domain.model.CareSection

fun CareSectionDto.careSection(): CareSection {
    return CareSection(
        id = id ?: -1,
        type = type ?: "Unknown",
        description = description ?: "No description"
    )
}
