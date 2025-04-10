package asgarov.elchin.plantly.authentication.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OtpVerifyDto(
    val email: String,
    val otp: String,
    val type: String
)