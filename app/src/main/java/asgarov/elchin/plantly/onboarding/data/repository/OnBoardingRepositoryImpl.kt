package asgarov.elchin.plantly.onboarding.data.repository

import asgarov.elchin.plantly.onboarding.data.local.OnboardingPreferences
import asgarov.elchin.plantly.onboarding.domain.repository.OnBoardingRepository
import kotlinx.coroutines.flow.Flow

class OnBoardingRepositoryImpl(
    private val preferences: OnboardingPreferences
):OnBoardingRepository {
    override fun isFirstLaunch(): Flow<Boolean> = preferences.isFirstLaunch()



    override suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        preferences.setFirstLaunch(isFirstLaunch)
    }
}