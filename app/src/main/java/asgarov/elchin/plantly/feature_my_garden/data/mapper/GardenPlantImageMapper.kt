package asgarov.elchin.plantly.feature_my_garden.data.mapper

import asgarov.elchin.plantly.feature_my_garden.data.remote.dto.GardenPlantImageDto
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlantImage

fun GardenPlantImageDto.toGardenPlantImage(): GardenPlantImage {
    return GardenPlantImage(
        originalUrl = originalUrl,
        regularUrl = regularUrl,
        mediumUrl = mediumUrl,
        smallUrl = smallUrl,
        thumbnailUrl = thumbnailUrl
    )
}

fun GardenPlantImage.toGardenPlantImageDto(): GardenPlantImageDto {
    return GardenPlantImageDto(
        originalUrl = originalUrl,
        regularUrl = regularUrl,
        mediumUrl = mediumUrl,
        smallUrl = smallUrl,
        thumbnailUrl = thumbnailUrl
    )
}