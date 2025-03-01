package asgarov.elchin.plantly.onboarding.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    fun isFirstLaunch(): Flow<Boolean>
    suspend fun setFirstLaunch(isFirstLaunch:Boolean)
}