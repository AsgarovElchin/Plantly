package asgarov.elchin.plantly.feature_reminder.di

import asgarov.elchin.plantly.feature_reminder.data.remote.ReminderApi
import asgarov.elchin.plantly.feature_reminder.data.repository.ReminderRepositoryImpl
import asgarov.elchin.plantly.feature_reminder.domain.repository.ReminderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReminderModule {

    @Provides
    @Singleton
    fun provideReminderApi(@Named("MainApi") retrofit: Retrofit): ReminderApi {
        return retrofit.create(ReminderApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlantRepository(api: ReminderApi): ReminderRepository {
        return ReminderRepositoryImpl(api)
    }
}