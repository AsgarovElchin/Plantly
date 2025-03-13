package asgarov.elchin.plantly.feature_explore.data.repository

import asgarov.elchin.plantly.BuildConfig
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.data.mapper.toPlantDetail
import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlantDetailRepositoryImpl @Inject constructor(
    private val plantsDataApi: PlantsDataApi
):PlantDetailRepository {
    override fun getPlantDetailsById(id: Int): Flow<Resource<PlantDetail>> {
        return flow{
            try {
                emit(Resource.Loading())
                val plantDetails = plantsDataApi.getPlantDetailsById(id, BuildConfig.API_KEY ).toPlantDetail()
                emit(Resource.Success(plantDetails))

            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }catch (e: IOException){
                emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }




}