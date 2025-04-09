package asgarov.elchin.plantly.authentication.data.remote.dto

data class OtpVerifyDto(
    val email: String,
    val otp: String
)