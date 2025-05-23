package asgarov.elchin.plantly.authentication.domain.repository

import asgarov.elchin.plantly.authentication.data.remote.dto.OtpRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.OtpVerifyDto
import asgarov.elchin.plantly.authentication.domain.model.Token
import asgarov.elchin.plantly.authentication.domain.model.TokenPair
import asgarov.elchin.plantly.authentication.domain.model.User
import asgarov.elchin.plantly.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun register(email: String, password: String): Flow<Resource<User>>

    fun login(email: String, password: String): Flow<Resource<TokenPair>>

    fun refreshToken(refreshToken: String): Flow<Resource<String>>

    fun logout(accessToken: String): Flow<Resource<Unit>>

    fun resetPassword(email: String, newPassword: String): Flow<Resource<Unit>>

    fun sendOtp(dto: OtpRequestDto): Flow<Resource<Unit>>

    fun verifyOtp(dto: OtpVerifyDto): Flow<Resource<Unit>>


}