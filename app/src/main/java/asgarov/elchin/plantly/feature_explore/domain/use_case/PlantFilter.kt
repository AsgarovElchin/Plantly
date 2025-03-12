package asgarov.elchin.plantly.feature_explore.domain.use_case

data class PlantFilter(
    val query: String? = null,
    val order: String? = null,
    val edible: Boolean? = null,
    val poisonous: Boolean? = null,
    val cycle: String? = null,
    val watering: String? = null,
    val sunlight: String? = null,
    val indoor: Boolean? = null
)