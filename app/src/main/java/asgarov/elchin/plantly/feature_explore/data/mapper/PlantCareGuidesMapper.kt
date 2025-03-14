package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantCareGuidesDto
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData

fun PlantCareGuidesDto.plantCareGuidesData(): List<PlantCareGuideData> {
    return this.data.map { it.plantCareGuideData() }
}