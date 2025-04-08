package asgarov.elchin.plantly.authentication.presentation.screen

data class LogoutState(
    val isLoading: Boolean = false,
    val isLoggedOut: Boolean = false,
    val error: String = ""
)

