package asgarov.elchin.plantly.feature_explore.domain.model

data class Plant(
    val id: Int,
    val commonName: String,
    val scientificNames: List<String>,
    val image: DefaultImage?
)