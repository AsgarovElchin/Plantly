package asgarov.elchin.plantly.feature_explore.data.repository

import asgarov.elchin.plantly.BuildConfig
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.data.mapper.toCategory
import asgarov.elchin.plantly.feature_explore.data.mapper.toPlant
import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val plantsDataApi: PlantsDataApi
):PlantRepository {
    override fun getAllPlantsByCategory(category: String): Flow<Resource<List<Plant>>> {
        return  flow {
            try {
                emit(Resource.Loading())
                val plants = plantsDataApi.getAllPlantsByCategory(category,"Bearer ${BuildConfig.API_KEY}").map { it.toPlant() }
                emit(Resource.Success(plants))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        e.localizedMessage
                            ?: "Couldn't reach server. Check your internet connection"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}