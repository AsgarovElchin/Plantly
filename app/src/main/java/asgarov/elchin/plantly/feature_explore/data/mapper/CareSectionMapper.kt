package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.CareSectionDto
import asgarov.elchin.plantly.feature_explore.domain.model.CareSection

fun CareSectionDto.careSection(): CareSection {
    return CareSection(
        id = this.id,
        type = this.type,
        description = this.description
    )
}