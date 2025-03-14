package asgarov.elchin.plantly.feature_explore.data.repository

import asgarov.elchin.plantly.BuildConfig
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.data.mapper.plantCareGuidesData
import asgarov.elchin.plantly.feature_explore.data.mapper.toPlantDetail
import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantCareRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlantCareRepositoryImpl @Inject constructor(
    private val plantsDataApi: PlantsDataApi
):PlantCareRepository {
    override fun getPlantCareGuidesById(speciesId: Int): Flow<Resource<List<PlantCareGuideData>>> {
        return flow{
            try {
                emit(Resource.Loading())
                val plantCareGuides = plantsDataApi.getPlantCareGuidesById(speciesId, BuildConfig.API_KEY ).plantCareGuidesData()
                emit(Resource.Success(plantCareGuides))
            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }catch (e: IOException){
                emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }


}