package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.google.gson.annotations.SerializedName


data class HardinessLocationDto(
    @SerializedName("full_iframe") val fullIframe: String,
    @SerializedName("full_url") val fullUrl: String
)