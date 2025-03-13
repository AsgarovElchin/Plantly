package asgarov.elchin.plantly.feature_explore.di

import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.data.repository.PlantDetailRepositoryImpl
import asgarov.elchin.plantly.feature_explore.data.repository.PlantRepositoryImpl
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantDetailRepository
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantRepository
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
    fun providePlantRepository(api: PlantsDataApi): PlantRepository {
        return PlantRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun providePlantDetailRepository(api: PlantsDataApi): PlantDetailRepository {
        return PlantDetailRepositoryImpl(api)
    }



}