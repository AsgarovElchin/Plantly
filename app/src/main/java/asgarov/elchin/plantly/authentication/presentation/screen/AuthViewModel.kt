package asgarov.elchin.plantly.authentication.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.authentication.domain.repository.AuthRepository
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

    fun register(email: String, password: String) {
        repository.register(email, password).onEach { result ->
            when (result) {
                is Resource.Loading -> _registerState.value = RegisterState(isLoading = true)
                is Resource.Success -> _registerState.value = RegisterState(user = result.data)
                is Resource.Error -> _registerState.value = RegisterState(error = result.message ?: "Unknown error")
            }
        }.launchIn(viewModelScope)
    }
}
