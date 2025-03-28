package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CareSectionDto(
    val description: String,
    val id: Int,
    val type: String
)