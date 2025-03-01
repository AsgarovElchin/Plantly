package asgarov.elchin.plantly.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.onboarding.domain.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repository: OnBoardingRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showOnboarding = MutableStateFlow(true)
    val showOnboarding: StateFlow<Boolean> = _showOnboarding

    init {
        viewModelScope.launch {
            repository.isFirstLaunch().collect { isFirstTime ->
                _showOnboarding.value = isFirstTime
                _isLoading.value = false
            }
        }
    }

    fun markOnboardingCompleted() {
        viewModelScope.launch {
            repository.setFirstLaunch(false)
            _showOnboarding.value = false
        }
    }
}