package asgarov.elchin.plantly.authentication.presentation.screen.login

import asgarov.elchin.plantly.authentication.domain.model.TokenPair

data class LoginState(
    val isLoading: Boolean = false,
    val tokens: TokenPair? = null,
    val error: String = ""
)