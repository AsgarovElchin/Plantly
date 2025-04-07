package asgarov.elchin.plantly.authentication.presentation.screen.register

import asgarov.elchin.plantly.authentication.domain.model.User

data class RegisterState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)
