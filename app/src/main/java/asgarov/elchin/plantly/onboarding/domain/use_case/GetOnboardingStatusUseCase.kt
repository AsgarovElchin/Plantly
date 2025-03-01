package asgarov.elchin.plantly.onboarding.domain.use_case

import asgarov.elchin.plantly.onboarding.domain.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow

class GetOnboardingStatusUseCase(
    private val repository: OnBoardingRepository
) {
    fun execute(): Flow<Boolean> = repository.isFirstLaunch()
}