package asgarov.elchin.plantly.authentication.presentation.screen


data class OtpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)