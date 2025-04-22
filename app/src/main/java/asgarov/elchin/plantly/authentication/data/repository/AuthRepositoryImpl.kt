package asgarov.elchin.plantly.authentication.data.repository

import android.util.Log
import asgarov.elchin.plantly.authentication.data.mapper.toTokenPair
import asgarov.elchin.plantly.authentication.data.mapper.toUser
import asgarov.elchin.plantly.authentication.data.remote.AuthApi
import asgarov.elchin.plantly.authentication.data.remote.dto.*
import asgarov.elchin.plantly.authentication.data.remote.util.parameterizedTypeOf
import asgarov.elchin.plantly.authentication.domain.model.TokenPair
import asgarov.elchin.plantly.authentication.domain.model.User
import asgarov.elchin.plantly.authentication.domain.repository.AuthRepository
import asgarov.elchin.plantly.core.utils.Resource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override fun register(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        val response = api.register(RegisterRequestDto(email, password))
        if (response.isSuccessful && response.body()?.success == true) {
            emit(Resource.Success(response.body()!!.data!!.toUser()))
        } else {
            emit(Resource.Error(response.body()?.message ?: "Registration failed"))
        }
    }.catch { e ->
        emit(Resource.Error("Error: ${e.localizedMessage}"))
    }

    override fun login(email: String, password: String): Flow<Resource<TokenPair>> = flow {
        emit(Resource.Loading())
        val response = api.login(LoginRequestDto(email, password))
        if (response.isSuccessful && response.body()?.success == true) {
            emit(Resource.Success(response.body()!!.data!!.toTokenPair()))
        } else {
            emit(Resource.Error(response.body()?.message ?: "Login failed"))
        }
    }.catch { e ->
        emit(Resource.Error("Error: ${e.localizedMessage}"))
    }

    override fun refreshToken(refreshToken: String): Flow<Resource<String>> = flow {
        val response = api.refreshToken(RefreshTokenRequestDto(refreshToken))
        val body = response.body()

        if (response.isSuccessful && body?.success == true && body.data != null) {
            emit(Resource.Success(body.data))
        } else {
            val errorMsg = body?.message ?: response.message()
            emit(Resource.Error("Refresh failed: $errorMsg"))
        }
    }.catch { e ->
        Log.e("RefreshRepo", "Exception during refresh: ${e.message}")
        emit(Resource.Error("Exception during refresh: ${e.localizedMessage}"))
    }


    override fun logout(accessToken: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val response = api.logout("Bearer $accessToken")
        if (response.isSuccessful && response.body()?.success == true) {
            emit(Resource.Success(Unit))
        } else {
            emit(Resource.Error(response.body()?.message ?: "Logout failed"))
        }
    }.catch { e ->
        emit(Resource.Error("Error: ${e.localizedMessage}"))
    }

    override fun resetPassword(email: String, newPassword: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val dto = ResetPasswordRequestDto(email = email, otp = "", newPassword = newPassword)
        val response = api.resetPassword(dto)
        if (response.isSuccessful && response.body()?.success == true) {
            emit(Resource.Success(Unit))
        } else {
            emit(Resource.Error(response.body()?.message ?: "Failed to reset password"))
        }
    }.catch { e ->
        emit(Resource.Error("Error: ${e.localizedMessage}"))
    }

    override fun sendOtp(dto: OtpRequestDto): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val response = api.sendOtp(dto)
        if (response.isSuccessful && response.body()?.success == true) {
            emit(Resource.Success(Unit))
        } else {
            emit(Resource.Error(response.body()?.message ?: "Failed to send OTP"))
        }
    }.catch { e ->
        emit(Resource.Error("Error: ${e.localizedMessage}"))
    }

    override fun verifyOtp(dto: OtpVerifyDto): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val response = api.verifyOtp(dto)
        if (response.isSuccessful && response.body()?.success == true) {
            emit(Resource.Success(Unit))
        } else {
            emit(Resource.Error(response.body()?.message ?: "Invalid or expired OTP"))
        }
    }.catch { e ->
        emit(Resource.Error("Error: ${e.localizedMessage}"))
    }


}
