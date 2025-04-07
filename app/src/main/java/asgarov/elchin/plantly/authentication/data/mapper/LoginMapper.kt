package asgarov.elchin.plantly.authentication.data.mapper

import asgarov.elchin.plantly.authentication.data.remote.dto.LoginResponseDto
import asgarov.elchin.plantly.authentication.domain.model.TokenPair

fun LoginResponseDto.toTokenPair(): TokenPair {
    return TokenPair(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}