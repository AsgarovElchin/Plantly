package asgarov.elchin.plantly.feature_scan.di

import asgarov.elchin.plantly.feature_scan.data.remote.GeminiApi
import asgarov.elchin.plantly.feature_scan.data.repository.GeminiRepositoryImpl
import asgarov.elchin.plantly.feature_scan.domain.repository.GeminiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScanModule {

    @Provides
    @Singleton
    fun provideGeminiApi(@Named("GeminiApi") retrofit: Retrofit): GeminiApi {
        return retrofit.create(GeminiApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeminiRepository(
        api: GeminiApi
    ): GeminiRepository {
        return GeminiRepositoryImpl(api,asgarov.elchin.plantly.BuildConfig.GEMINI_API_KEY)
    }
}
