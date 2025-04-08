package asgarov.elchin.plantly.authentication.presentation.screen

data class ResetPasswordState(
    val isLoading: Boolean = false,
    val passwordReset: Boolean = false,
    val error: String = ""
)