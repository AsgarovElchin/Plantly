package asgarov.elchin.plantly.feature_my_garden.data.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_my_garden.data.mapper.toGardenPlant
import asgarov.elchin.plantly.feature_my_garden.data.mapper.toGardenPlantDto
import asgarov.elchin.plantly.feature_my_garden.data.remote.PersonalPlantApi
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant
import asgarov.elchin.plantly.feature_my_garden.domain.repository.GardenPlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GardenPlantRepositoryImpl @Inject constructor(
    private val personalPlantApi: PersonalPlantApi
):GardenPlantRepository {

    override fun addGardenPlant(gardenPlant: GardenPlant): Flow<Resource<GardenPlant>> = flow {
        emit(Resource.Loading())

        try {
            val response = personalPlantApi.addGardenPlant(gardenPlant.toGardenPlantDto())
            if (response.isSuccessful) {
                response.body()?.let { emit(Resource.Success(it.toGardenPlant())) }
                    ?: emit(Resource.Error("Failed to add plant"))
            } else {
                emit(Resource.Error("Error adding plant: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }

    override fun getAllGardenPlants(): Flow<Resource<List<GardenPlant>>> = flow {
        emit(Resource.Loading())

        try {
            val response = personalPlantApi.getAllGardenPlants()
            if (response.isSuccessful) {
                response.body()?.let { plantDtos ->
                    emit(Resource.Success(plantDtos.map { it.toGardenPlant() }))
                } ?: emit(Resource.Error("No plants found"))
            } else {
                emit(Resource.Error("Error fetching plants: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }


    override fun deleteGardenPlant(id: Long): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        try {
            val response = personalPlantApi.deleteGardenPlant(id)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else if (response.code() == 404) {
                emit(Resource.Error("Plant not found"))
            } else {
                emit(Resource.Error("Error deleting plant: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }





}