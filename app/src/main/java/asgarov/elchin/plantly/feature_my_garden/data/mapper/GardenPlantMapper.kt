package asgarov.elchin.plantly.feature_my_garden.data.mapper

import asgarov.elchin.plantly.feature_my_garden.data.remote.dto.GardenPlantDto
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant

fun GardenPlantDto.toGardenPlant(): GardenPlant {
    return GardenPlant(
        id = this.id,
        commonName = this.commonName,
        scientificNames = this.scientificNames,
        defaultImage = this.defaultImage?.toGardenPlantImage()
    )
}

fun GardenPlant.toGardenPlantDto(): GardenPlantDto {
    return GardenPlantDto(
        id = this.id,
        commonName = this.commonName,
        scientificNames = this.scientificNames,
        defaultImage = this.defaultImage?.toGardenPlantImageDto()
    )
}