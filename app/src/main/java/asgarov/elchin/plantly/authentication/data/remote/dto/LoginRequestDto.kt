package asgarov.elchin.plantly.authentication.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDto(
    val email: String,
    val password: String
)