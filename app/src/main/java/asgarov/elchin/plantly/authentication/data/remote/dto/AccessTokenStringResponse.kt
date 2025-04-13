package asgarov.elchin.plantly.authentication.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessTokenStringResponse(
    val success: Boolean,
    val message: String,
    val data: String
)
