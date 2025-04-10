package asgarov.elchin.plantly.authentication.data.repository

import asgarov.elchin.plantly.authentication.data.mapper.toToken
import asgarov.elchin.plantly.authentication.data.mapper.toTokenPair
import asgarov.elchin.plantly.authentication.data.mapper.toUser
import asgarov.elchin.plantly.authentication.data.remote.AuthApi
import asgarov.elchin.plantly.authentication.data.remote.dto.LoginRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.OtpRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.OtpVerifyDto
import asgarov.elchin.plantly.authentication.data.remote.dto.RegisterRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.ResetPasswordRequestDto
import asgarov.elchin.plantly.authentication.domain.model.Token
import asgarov.elchin.plantly.authentication.domain.model.TokenPair
import asgarov.elchin.plantly.authentication.domain.model.User
import asgarov.elchin.plantly.authentication.domain.repository.AuthRepository
import asgarov.elchin.plantly.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override fun register(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.register(RegisterRequestDto(email, password))
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(response.body()!!.data!!.toUser()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Registration failed"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }

    override fun login(email: String, password: String): Flow<Resource<TokenPair>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.login(LoginRequestDto(email, password))
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(response.body()!!.data!!.toTokenPair()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Login failed"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }

    override fun refreshToken(refreshToken: String): Flow<Resource<Token>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.refreshToken(refreshToken)
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(response.body()!!.data!!.toToken()))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Failed to refresh token"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }

    override fun logout(accessToken: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.logout("Bearer $accessToken")
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Logout failed"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }

    override fun resetPassword(email: String, newPassword: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val dto = ResetPasswordRequestDto(email = email, otp = "", newPassword = newPassword)
            val response = api.resetPassword(dto)
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Failed to reset password"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }

    override fun sendOtp(dto: OtpRequestDto): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.sendOtp(dto)
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Failed to send OTP"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }

    override fun verifyOtp(dto: OtpVerifyDto): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.verifyOtp(dto)
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Invalid or expired OTP"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
}