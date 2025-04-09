package asgarov.elchin.plantly.authentication.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.authentication.domain.repository.AuthRepository
import asgarov.elchin.plantly.authentication.presentation.screen.login.LoginState
import asgarov.elchin.plantly.authentication.presentation.screen.register.RegisterState
import asgarov.elchin.plantly.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
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
            when (result) {
                is Resource.Loading -> _registerState.value = RegisterState(isLoading = true)
                is Resource.Success -> _registerState.value = RegisterState(user = result.data)
                is Resource.Error -> _registerState.value = RegisterState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }


    fun login(email: String, password: String) {
        repository.login(email, password).onEach { result ->
            when (result) {
                is Resource.Loading -> _loginState.value = LoginState(isLoading = true)
                is Resource.Success -> _loginState.value = LoginState(tokens = result.data)
                is Resource.Error -> _loginState.value = LoginState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }

    fun refreshToken(refreshToken: String) {
        repository.refreshToken(refreshToken).onEach { result ->
            when (result) {
                is Resource.Loading -> _refreshState.value = RefreshTokenState(isLoading = true)
                is Resource.Success -> _refreshState.value = RefreshTokenState(token = result.data)
                is Resource.Error -> _refreshState.value = RefreshTokenState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }


    fun logout(accessToken: String) {
        repository.logout(accessToken).onEach { result ->
            when (result) {
                is Resource.Loading -> _logoutState.value = LogoutState(isLoading = true)
                is Resource.Success -> _logoutState.value = LogoutState(isLoggedOut = true)
                is Resource.Error -> _logoutState.value = LogoutState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }


    fun forgotPassword(email: String) {
        repository.forgotPassword(email).onEach { result ->
            when (result) {
                is Resource.Loading -> _forgotPasswordState.value = ForgotPasswordState(isLoading = true)
                is Resource.Success -> _forgotPasswordState.value = ForgotPasswordState(otpSent = true)
                is Resource.Error -> _forgotPasswordState.value = ForgotPasswordState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }



    fun resetPassword(email: String, otp: String, newPassword: String) {
        repository.resetPassword(email, otp, newPassword).onEach { result ->
            when (result) {
                is Resource.Loading -> _resetPasswordState.value = ResetPasswordState(isLoading = true)
                is Resource.Success -> _resetPasswordState.value = ResetPasswordState(passwordReset = true)
                is Resource.Error -> _resetPasswordState.value = ResetPasswordState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }

    fun sendOtp(email: String) {
        repository.sendOtp(email).onEach { result ->
            _otpSendState.value = when (result) {
                is Resource.Loading -> OtpState(isLoading = true)
                is Resource.Success -> OtpState(isSuccess = true)
                is Resource.Error -> OtpState(error = result.message ?: "Error sending OTP")
            }
        }.launchIn(viewModelScope)
    }

    fun verifyOtp(email: String, otp: String) {
        repository.verifyOtp(email, otp).onEach { result ->
            _otpVerifyState.value = when (result) {
                is Resource.Loading -> OtpState(isLoading = true)
                is Resource.Success -> OtpState(isSuccess = true)
                is Resource.Error -> OtpState(error = result.message ?: "OTP verification failed")
            }
        }.launchIn(viewModelScope)
    }

}
