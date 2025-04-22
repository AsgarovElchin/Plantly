package asgarov.elchin.plantly.authentication.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.authentication.TokenManager
import asgarov.elchin.plantly.authentication.data.remote.dto.OtpRequestDto
import asgarov.elchin.plantly.authentication.data.remote.dto.OtpVerifyDto
import asgarov.elchin.plantly.authentication.domain.repository.AuthRepository
import asgarov.elchin.plantly.authentication.presentation.screen.login.LoginState
import asgarov.elchin.plantly.authentication.presentation.screen.register.RegisterState
import asgarov.elchin.plantly.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    private val _refreshState = MutableStateFlow(RefreshTokenState())
    val refreshState: StateFlow<RefreshTokenState> = _refreshState

    private val _logoutState = MutableStateFlow(LogoutState())
    val logoutState: StateFlow<LogoutState> = _logoutState

    private val _forgotPasswordState = MutableStateFlow(ForgotPasswordState())
    val forgotPasswordState: StateFlow<ForgotPasswordState> = _forgotPasswordState

    private val _resetPasswordState = MutableStateFlow(ResetPasswordState())
    val resetPasswordState: StateFlow<ResetPasswordState> = _resetPasswordState

    private val _otpSendState = MutableStateFlow(OtpState())
    val otpSendState: StateFlow<OtpState> = _otpSendState

    private val _otpVerifyState = MutableStateFlow(OtpState())
    val otpVerifyState: StateFlow<OtpState> = _otpVerifyState

    fun register(email: String, password: String) {
        repository.register(email, password).onEach { result ->
            _registerState.value = when (result) {
                is Resource.Loading -> RegisterState(isLoading = true)
                is Resource.Success -> RegisterState(user = result.data)
                is Resource.Error -> RegisterState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }

    fun login(email: String, password: String) {
        repository.login(email, password).onEach { result ->
            _loginState.value = when (result) {
                is Resource.Loading -> LoginState(isLoading = true)
                is Resource.Success -> {
                    viewModelScope.launch {
                        tokenManager.saveTokens(
                            result.data!!.accessToken,
                            result.data.refreshToken
                        )
                        Log.d("LoginDebug", "Access Token: ${result.data?.accessToken}")
                        Log.d("LoginDebug", "Refresh Token: ${result.data?.refreshToken}")

                        delay(1000)
                    }
                    LoginState(tokens = result.data)
                }
                is Resource.Error -> LoginState(error = result.message ?: "Login failed")
            }
        }.launchIn(viewModelScope)
    }

    fun refreshToken(refreshToken: String) {
        Log.d("AuthViewModel", "Attempting to refresh token: $refreshToken")

        repository.refreshToken(refreshToken).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("AuthViewModel", "Refreshing token...")
                    _refreshState.value = RefreshTokenState(isLoading = true)
                }

                is Resource.Success -> {
                    Log.d("AuthViewModel", "Token refresh success! New access token: ${result.data}")
                    _refreshState.value = RefreshTokenState(token = result.data)
                }

                is Resource.Error -> {
                    Log.e("AuthViewModel", "Token refresh failed: ${result.message}")
                    _refreshState.value = RefreshTokenState(error = result.message ?: "Unknown error")
                }
            }
        }.launchIn(viewModelScope)
    }


    fun sendOtp(email: String, type: String) {
        repository.sendOtp(OtpRequestDto(email, type)).onEach { result ->
            _otpSendState.value = when (result) {
                is Resource.Loading -> OtpState(isLoading = true)
                is Resource.Success -> OtpState(isSuccess = true)
                is Resource.Error -> OtpState(error = result.message ?: "Error sending OTP")
            }
        }.launchIn(viewModelScope)
    }

    fun verifyOtp(email: String, otp: String, type: String) {

        repository.verifyOtp(OtpVerifyDto(email, otp, type)).onEach { result ->
            _otpVerifyState.value = when (result) {
                is Resource.Loading -> {
                    OtpState(isLoading = true)
                }
                is Resource.Success -> {
                    OtpState(isSuccess = true)
                }
                is Resource.Error -> {
                    OtpState(error = result.message ?: "OTP verification failed")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetPassword(email: String, newPassword: String) {
        repository.resetPassword(email, newPassword).onEach { result ->
            _resetPasswordState.value = when (result) {
                is Resource.Loading -> ResetPasswordState(isLoading = true)
                is Resource.Success -> ResetPasswordState(passwordReset = true)
                is Resource.Error -> ResetPasswordState(error = result.message ?: "Reset failed")
            }
        }.launchIn(viewModelScope)
    }

    fun logout() {
        Log.d("LogoutDebug", "Logout called")

        viewModelScope.launch {
            val accessToken = tokenManager.accessToken.first()
            val refreshToken = tokenManager.refreshToken.first()
            Log.d("LogoutDebug", "Token: $accessToken")
            Log.d("LogoutDebug", "Token: $refreshToken")

            repository.logout(accessToken).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d("LogoutDebug", "Logging out...")
                        _logoutState.value = LogoutState(isLoading = true)
                    }
                    is Resource.Success -> {
                        Log.d("LogoutDebug", "Logout success!")
                        tokenManager.clearTokens()
                        _logoutState.value = LogoutState(isLoggedOut = true)
                    }
                    is Resource.Error -> {
                        Log.e("LogoutDebug", "Logout failed: ${result.message}")
                        _logoutState.value = LogoutState(error = result.message ?: "Logout failed")
                    }
                }
            }.launchIn(this)
        }
    }


}