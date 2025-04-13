package asgarov.elchin.plantly.authentication.presentation.screen

data class RefreshTokenState(
    val isLoading: Boolean = false,
    val token: String? = null,
    val error: String = ""
)