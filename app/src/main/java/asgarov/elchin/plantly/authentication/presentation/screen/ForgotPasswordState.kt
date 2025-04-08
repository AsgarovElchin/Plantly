package asgarov.elchin.plantly.authentication.presentation.screen

data class ForgotPasswordState(
    val isLoading: Boolean = false,
    val otpSent: Boolean = false,
    val error: String = ""
)