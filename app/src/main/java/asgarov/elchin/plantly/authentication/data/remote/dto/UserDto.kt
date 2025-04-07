package asgarov.elchin.plantly.authentication.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val id: Long,
    val email: String,
    val password: String,
    val refreshToken: String?,
    val resetOtp: String?,
    val resetOtpExpiry: String?
)