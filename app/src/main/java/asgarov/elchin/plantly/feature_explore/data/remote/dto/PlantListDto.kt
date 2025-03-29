package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantListDto(
    @Json(name = "current_page") val currentPage: Int?,
    @Json(name = "data") val data: List<PlantDto>?,
    @Json(name = "from") val from: Int?,
    @Json(name = "last_page") val lastPage: Int?,
    @Json(name = "per_page") val perPage: Int?,
    @Json(name = "to") val to: Int?,
    @Json(name = "total") val total: Int?
)