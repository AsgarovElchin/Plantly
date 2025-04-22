package asgarov.elchin.plantly.authentication


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.onboarding.domain.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val onboardingRepository: OnBoardingRepository
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        viewModelScope.launch {
            val isFirstLaunch = onboardingRepository.isFirstLaunch().first()
            if (isFirstLaunch) {
                _startDestination.value = "onboarding_graph"
            } else {
                val token = tokenManager.accessToken.first()
                _startDestination.value = if (token.isNotBlank()) "main_graph" else "authentication_graph"
            }
        }
    }
}
