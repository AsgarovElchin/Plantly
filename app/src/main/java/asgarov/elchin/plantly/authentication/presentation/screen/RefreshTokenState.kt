package asgarov.elchin.plantly.authentication.presentation.screen

import asgarov.elchin.plantly.authentication.domain.model.Token

data class RefreshTokenState(
    val isLoading: Boolean = false,
    val token: Token? = null,
    val error: String = ""
)