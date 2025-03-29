package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DefaultImageDto(
    @Json(name = "license") val license: Int?,
    @Json(name = "license_name") val licenseName: String?,
    @Json(name = "license_url") val licenseUrl: String?,
    @Json(name = "medium_url") val mediumUrl: String?,
    @Json(name = "original_url") val originalUrl: String?,
    @Json(name = "regular_url") val regularUrl: String?,
    @Json(name = "small_url") val smallUrl: String?,
    @Json(name = "thumbnail") val thumbnailUrl: String?
)