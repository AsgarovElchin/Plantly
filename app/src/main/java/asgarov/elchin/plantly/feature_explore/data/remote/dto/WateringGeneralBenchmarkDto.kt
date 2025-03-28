package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WateringGeneralBenchmarkDto(
    val unit: String,
    val value: Any
)