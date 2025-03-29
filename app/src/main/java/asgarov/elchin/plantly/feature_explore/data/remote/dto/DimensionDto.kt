package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DimensionDto(
    @Json(name = "max_value") val maxValue: Int?,
    @Json(name = "min_value") val minValue: Int?,
    @Json(name = "type") val type: String?,
    @Json(name = "unit") val unit: String?
)