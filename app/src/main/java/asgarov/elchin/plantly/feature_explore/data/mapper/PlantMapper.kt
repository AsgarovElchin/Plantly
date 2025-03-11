package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantDto
import asgarov.elchin.plantly.feature_explore.domain.model.Plant

fun PlantDto.toPlant():Plant{
    return Plant(
        id = id ?: "Unknown ID",
        imageUrl = imageUrl ?: "",
        commonNames = commonNames ?: listOf("Unknown Common Name"),
        latinName = latinName ?: "Unknown Latin Name",
        family = family ?: "Unknown Family"
    )
}