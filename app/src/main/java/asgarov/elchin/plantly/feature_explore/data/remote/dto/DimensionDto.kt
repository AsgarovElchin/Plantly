package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DimensionDto(
    @SerializedName("max_value") val maxValue: Int,
    @SerializedName("min_value") val minValue: Int,
    @SerializedName("type") val type: String,
    @SerializedName("unit") val unit: String
)