package asgarov.elchin.plantly.onboarding.di

import android.content.Context
import asgarov.elchin.plantly.onboarding.data.local.OnboardingPreferences
import asgarov.elchin.plantly.onboarding.data.repository.OnBoardingRepositoryImpl
import asgarov.elchin.plantly.onboarding.domain.repository.OnBoardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnBoardingModule{

    @Provides
    @Singleton
    fun provideOnBoardingPreferences(@ApplicationContext context: Context):OnboardingPreferences{
        return OnboardingPreferences(context)
    }

    @Provides
    @Singleton
    fun provideOnBoardingRepository(onboardingPreferences: OnboardingPreferences):OnBoardingRepository{
        return OnBoardingRepositoryImpl(onboardingPreferences)
    }

}