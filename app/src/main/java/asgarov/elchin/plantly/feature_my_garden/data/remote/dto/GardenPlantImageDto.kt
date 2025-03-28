package asgarov.elchin.plantly.feature_my_garden.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GardenPlantImageDto(
    val originalUrl: String?,
    val regularUrl: String?,
    val mediumUrl: String?,
    val smallUrl: String?,
    val thumbnailUrl: String?
)