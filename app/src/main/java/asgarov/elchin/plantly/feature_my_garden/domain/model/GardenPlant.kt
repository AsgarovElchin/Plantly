package asgarov.elchin.plantly.feature_my_garden.domain.model


data class GardenPlant(
    val id: Long,
    val commonName: String,
    val scientificNames: List<String>,
    val defaultImage: GardenPlantImage?
)