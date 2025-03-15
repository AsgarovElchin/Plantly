package asgarov.elchin.plantly.feature_my_garden.data.remote.dto

data class GardenPlantDto(
   val id: Long,
   val commonName: String,
   val scientificNames: List<String>,
   val defaultImage: GardenPlantImageDto?
)