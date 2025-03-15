package asgarov.elchin.plantly.feature_my_garden.data.mapper

import asgarov.elchin.plantly.feature_my_garden.data.remote.dto.GardenPlantImageDto
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlantImage

fun GardenPlantImageDto.toGardenPlantImage(): GardenPlantImage {
    return GardenPlantImage(
        originalUrl = this.originalUrl,
        regularUrl = this.regularUrl,
        mediumUrl = this.mediumUrl,
        smallUrl = this.smallUrl,
        thumbnailUrl = this.thumbnailUrl
    )
}

fun GardenPlantImage.toGardenPlantImageDto(): GardenPlantImageDto {
    return GardenPlantImageDto(
        originalUrl = this.originalUrl,
        regularUrl = this.regularUrl,
        mediumUrl = this.mediumUrl,
        smallUrl = this.smallUrl,
        thumbnailUrl = this.thumbnailUrl
    )
}