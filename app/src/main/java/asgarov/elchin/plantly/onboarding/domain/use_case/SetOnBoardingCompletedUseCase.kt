package asgarov.elchin.plantly.onboarding.domain.use_case

import asgarov.elchin.plantly.onboarding.domain.repository.OnBoardingRepository

class SetOnBoardingCompletedUseCase(
    private val repository: OnBoardingRepository
) {
    suspend fun execute() = repository.setFirstLaunch(false)
}