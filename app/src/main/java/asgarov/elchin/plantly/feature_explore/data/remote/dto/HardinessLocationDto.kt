package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class HardinessLocationDto(
    @Json(name = "full_iframe") val fullIframe: String?,
    @Json(name = "full_url") val fullUrl: String?
)