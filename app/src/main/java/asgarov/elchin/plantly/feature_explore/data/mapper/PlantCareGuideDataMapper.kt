package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantCareGuideDataDto
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData

fun PlantCareGuideDataDto.plantCareGuideData(): PlantCareGuideData {
    return PlantCareGuideData(
        id = this.id,
        speciesId = this.speciesId,
        commonName = this.commonName,
        scientificName = this.scientificName,
        careSections = this.sections.map { it.careSection() }
    )
}