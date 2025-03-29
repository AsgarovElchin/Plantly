package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantDetailDto
import asgarov.elchin.plantly.feature_explore.domain.model.DefaultImage
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail

fun PlantDetailDto.toPlantDetail(): PlantDetail {
    return PlantDetail(
        id = id ?: -1,
        description = description ?: "No description",
        commonName = commonName ?: "Unknown",
        scientificName = scientificName.orEmpty(),
        defaultImage = defaultImage?.toDefaultImage() ?: DefaultImage("", "", "", "", ""),
        genus = genus ?: "Unknown",
        type = type ?: "Unknown",
        cycle = cycle ?: "Unknown",
        growthRate = growthRate ?: "Unknown",
        watering = watering ?: "Unknown",
        sunlight = sunlight.orEmpty(),
        hardiness = hardiness?.toHardiness(),
        droughtTolerant = droughtTolerant ?: false,
        indoor = indoor ?: false,
        edibleFruit = edibleFruit ?: false,
        edibleLeaf = edibleLeaf ?: false,
        poisonousToHumans = poisonousToHumans ?: false,
        poisonousToPets = poisonousToPets ?: false
    )
}
