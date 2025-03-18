package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantCareGuideDataDto
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData

fun PlantCareGuideDataDto.plantCareGuideData(): PlantCareGuideData {
    return PlantCareGuideData(
        id = id,
        speciesId = speciesId,
        commonName = commonName,
        scientificName = scientificName,
        careSections = sections.map { it.careSection() }
    )
}