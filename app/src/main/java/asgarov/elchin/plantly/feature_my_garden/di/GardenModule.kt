package asgarov.elchin.plantly.feature_my_garden.di

import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.data.repository.PlantRepositoryImpl
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantRepository
import asgarov.elchin.plantly.feature_my_garden.data.remote.PersonalPlantApi
import asgarov.elchin.plantly.feature_my_garden.data.repository.GardenPlantRepositoryImpl
import asgarov.elchin.plantly.feature_my_garden.domain.repository.GardenPlantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GardenModule {

    @Provides
    @Singleton
    fun providePersonalPlantApi(@Named("MainApi") retrofit: Retrofit): PersonalPlantApi {
        return retrofit.create(PersonalPlantApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlantRepository(api: PersonalPlantApi): GardenPlantRepository {
        return GardenPlantRepositoryImpl(api)
    }
}