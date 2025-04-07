package asgarov.elchin.plantly.authentication.domain.model

data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)