package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerializedName ("Category") val category: String
)
