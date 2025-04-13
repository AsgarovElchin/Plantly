package asgarov.elchin.plantly.authentication.data.remote

import asgarov.elchin.plantly.authentication.data.remote.dto.AccessTokenStringResponse
import asgarov.elchin.plantly.authentication.data.remote.dto.ApiResponseDto
import asgarov.elchin.plantly.authentication.data.remote.dto.LoginRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.LoginResponseDto
import asgarov.elchin.plantly.authentication.data.remote.dto.OtpRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.OtpVerifyDto
import asgarov.elchin.plantly.authentication.data.remote.dto.RefreshTokenRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.RegisterRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.ResetPasswordRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    suspend fun register(@Body request: RegisterRequestDto): Response<ApiResponseDto<UserDto>>

    @POST("login")
    suspend fun login(@Body request: LoginRequestDto): Response<ApiResponseDto<LoginResponseDto>>

    @POST("refresh")
    suspend fun refreshToken(@Body dto: RefreshTokenRequestDto): Response<ApiResponseDto<String>>

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") accessToken: String): Response<ApiResponseDto<Unit>>

    @POST("reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequestDto): Response<ApiResponseDto<Unit>>

    @POST("api/otp/send")
    suspend fun sendOtp(@Body dto: OtpRequestDto): Response<ApiResponseDto<Unit>>

    @POST("api/otp/verify")
    suspend fun verifyOtp(@Body dto: OtpVerifyDto): Response<ApiResponseDto<Unit>>






}