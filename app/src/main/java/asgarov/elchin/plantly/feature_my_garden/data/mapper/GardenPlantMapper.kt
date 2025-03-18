package asgarov.elchin.plantly.feature_my_garden.data.mapper

import asgarov.elchin.plantly.feature_my_garden.data.remote.dto.GardenPlantDto
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant

fun GardenPlantDto.toGardenPlant(): GardenPlant {
    return GardenPlant(
        id = id,
        commonName = commonName,
        scientificNames = scientificNames,
        defaultImage = defaultImage?.toGardenPlantImage()
    )
}

fun GardenPlant.toGardenPlantDto(): GardenPlantDto {
    return GardenPlantDto(
        id = id,
        commonName = commonName,
        scientificNames = scientificNames,
        defaultImage = defaultImage?.toGardenPlantImageDto()
    )
}