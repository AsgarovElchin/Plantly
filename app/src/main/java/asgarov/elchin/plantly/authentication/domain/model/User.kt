package asgarov.elchin.plantly.authentication.domain.model

data class User(
    val id: Long,
    val email: String,
    val refreshToken: String?,
    val resetOtp: String?,
    val resetOtpExpiry: String?
)