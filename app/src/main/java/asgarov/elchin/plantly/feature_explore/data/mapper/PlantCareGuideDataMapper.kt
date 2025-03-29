package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantCareGuideDataDto
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData

fun PlantCareGuideDataDto.plantCareGuideData(): PlantCareGuideData {
    return PlantCareGuideData(
        id = id ?: -1,
        speciesId = speciesId ?: -1,
        commonName = commonName ?: "Unknown",
        scientificName = scientificName ?: emptyList(),
        careSections = sections.orEmpty()
            .filterNotNull()
            .map { it.careSection() }

    )
}
