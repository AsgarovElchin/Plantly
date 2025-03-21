package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlantListDto(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("data") val data: List<PlantDto>,
    @SerializedName("from") val from: Int,
    @SerializedName("last_page") val lastPage: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("to") val to: Int,
    @SerializedName("total") val total: Int
)