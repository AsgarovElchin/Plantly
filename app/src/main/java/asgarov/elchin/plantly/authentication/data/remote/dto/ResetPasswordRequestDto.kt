package asgarov.elchin.plantly.authentication.data.remote.dto

data class ResetPasswordRequestDto(
    val email: String,
    val otp: String,
    val newPassword: String
)