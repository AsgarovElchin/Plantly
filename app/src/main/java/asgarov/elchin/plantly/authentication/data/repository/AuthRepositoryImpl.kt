package asgarov.elchin.plantly.authentication.data.repository

import asgarov.elchin.plantly.authentication.data.mapper.toTokenPair
import asgarov.elchin.plantly.authentication.data.mapper.toUser
import asgarov.elchin.plantly.authentication.data.remote.AuthApi
import asgarov.elchin.plantly.authentication.data.remote.dto.LoginRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.RegisterRequestDto
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
                val data = response.body()!!.data!!.toTokenPair()
                emit(Resource.Success(data))
            } else {
                emit(Resource.Error(response.body()?.message ?: "Login failed"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.localizedMessage}"))
        }
    }
}