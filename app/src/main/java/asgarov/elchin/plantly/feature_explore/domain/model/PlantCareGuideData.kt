package asgarov.elchin.plantly.feature_explore.domain.model

data class PlantCareGuideData(
    val id: Int,
    val speciesId: Int,
    val commonName: String,
    val scientificName: List<String>,
    val careSections: List<CareSection>
)