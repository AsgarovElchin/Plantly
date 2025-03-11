package asgarov.elchin.plantly.feature_explore.di

import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.data.repository.CategoryRepositoryImpl
import asgarov.elchin.plantly.feature_explore.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExploreModule {

    @Singleton
    @Provides
    fun providePlantsDataApi(retrofit: Retrofit): PlantsDataApi {
        return retrofit.create(PlantsDataApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(api: PlantsDataApi): CategoryRepository {
        return CategoryRepositoryImpl(api)
    }

}