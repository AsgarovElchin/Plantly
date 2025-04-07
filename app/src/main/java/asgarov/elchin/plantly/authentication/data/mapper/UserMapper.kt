package asgarov.elchin.plantly.authentication.data.mapper

import asgarov.elchin.plantly.authentication.data.remote.dto.UserDto
import asgarov.elchin.plantly.authentication.domain.model.User

fun UserDto.toUser(): User {
    return User(
        id = id,
        email = email,
        refreshToken = refreshToken,
        resetOtp = resetOtp,
        resetOtpExpiry = resetOtpExpiry
    )
}