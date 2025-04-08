package asgarov.elchin.plantly.authentication.data.mapper

import asgarov.elchin.plantly.authentication.domain.model.Token

fun String.toToken(): Token {
    return Token(accessToken = this)
}