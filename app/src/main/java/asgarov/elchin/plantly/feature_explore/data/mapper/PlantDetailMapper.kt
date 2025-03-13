package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantDetailDto
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail

fun PlantDetailDto.toPlantDetail(): PlantDetail {
    return PlantDetail(
        id = id,
        commonName = commonName,
        scientificName = scientificName,
        defaultImage = defaultImage.toDefaultImage(),
        description = description,
        genus = genus,
        type = type,
        cycle = cycle,
        growthRate = growthRate,
        watering = watering,
        sunlight = sunlight,
        hardiness = hardiness.toHardiness(),
        droughtTolerant = droughtTolerant,
        indoor = indoor,
        edibleFruit = edibleFruit,
        edibleLeaf = edibleLeaf,
        poisonousToHumans = poisonousToHumans,
        poisonousToPets = poisonousToPets
    )
}