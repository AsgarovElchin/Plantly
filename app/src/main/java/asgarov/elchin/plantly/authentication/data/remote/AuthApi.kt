package asgarov.elchin.plantly.authentication.data.remote

import asgarov.elchin.plantly.authentication.data.remote.dto.ApiResponseDto
import asgarov.elchin.plantly.authentication.data.remote.dto.LoginRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.LoginResponseDto
import asgarov.elchin.plantly.authentication.data.remote.dto.RegisterRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("register")
    suspend fun register(@Body request: RegisterRequestDto): Response<ApiResponseDto<UserDto>>

    @POST("login")
    suspend fun login(@Body request: LoginRequestDto): Response<ApiResponseDto<LoginResponseDto>>

    @POST("refresh")
    suspend fun refreshToken(@Query("refreshToken") refreshToken: String): Response<ApiResponseDto<String>>

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") accessToken: String): Response<ApiResponseDto<Unit>>



}