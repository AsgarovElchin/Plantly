package asgarov.elchin.plantly.feature_explore.domain.model

data class PlantDetail(
    val id: Int,
    val description: String,
    val commonName: String,
    val scientificName: List<String>,
    val defaultImage: DefaultImage,
    val genus: String,
    val type: String,
    val cycle: String,
    val growthRate: String,
    val watering: String,
    val sunlight: List<String>,
    val hardiness: Hardiness?,
    val droughtTolerant: Boolean,
    val indoor: Boolean,
    val edibleFruit: Boolean,
    val edibleLeaf: Boolean,
    val poisonousToHumans: Boolean,
    val poisonousToPets: Boolean
)
