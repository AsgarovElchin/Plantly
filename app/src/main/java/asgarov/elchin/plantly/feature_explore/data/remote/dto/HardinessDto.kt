package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HardinessDto(
    val max: String,
    val min: String
)