package asgarov.elchin.plantly.authentication.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtpRequestDto(
    val email: String,
    val type: String
)