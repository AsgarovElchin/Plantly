package asgarov.elchin.plantly.authentication.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResetPasswordRequestDto(
    val email: String,
    val otp: String,
    val newPassword: String
)