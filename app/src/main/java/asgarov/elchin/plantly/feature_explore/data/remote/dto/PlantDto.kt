package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantDto(
    @Json(name = "authority") val authority: String?,
    @Json(name = "common_name") val commonName: String,
    @Json(name = "cultivar") val cultivar: String?,
    @Json(name = "default_image") val defaultImage: DefaultImageDto?,
    @Json(name = "family") val family: String?,
    @Json(name = "genus") val genus: String?,
    @Json(name = "hybrid") val hybrid: String?,
    @Json(name = "id") val id: Int,
    @Json(name = "other_name") val otherNames: List<String>,
    @Json(name = "scientific_name") val scientificNames: List<String>,
    @Json(name = "species_epithet") val speciesEpithet: String?,
    @Json(name = "subspecies") val subspecies: String?,
    @Json(name = "variety") val variety: String?
)