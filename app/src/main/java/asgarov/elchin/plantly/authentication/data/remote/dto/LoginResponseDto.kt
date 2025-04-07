package asgarov.elchin.plantly.authentication.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String
)