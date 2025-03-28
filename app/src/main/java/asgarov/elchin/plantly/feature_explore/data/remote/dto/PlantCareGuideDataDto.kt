package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantCareGuideDataDto(
    @Json(name = "common_name") val commonName: String,
    @Json(name = "id") val id: Int,
    @Json(name = "scientific_name") val scientificName: List<String>,
    @Json(name = "section") val sections: List<CareSectionDto>,
    @Json(name = "species_id") val speciesId: Int
)