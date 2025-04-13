package asgarov.elchin.plantly.feature_my_garden.data.repository

import android.util.Log
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
) : GardenPlantRepository {

    override fun addGardenPlant(gardenPlant: GardenPlant): Flow<Resource<GardenPlant>> = flow {
        emit(Resource.Loading())
        try {
            val response = personalPlantApi.addGardenPlant(gardenPlant.toGardenPlantDto())
            val body = response.body()


            if (response.isSuccessful && body != null) {
                if (body.success && body.data != null) {
                    emit(Resource.Success(body.data.toGardenPlant()))
                } else {
                    emit(Resource.Error(body.message.ifBlank { "Failed to add plant" }))
                }
            } else {
                val error = response.errorBody()?.string() ?: response.message()
                Log.e("GardenRepo", "Error: $error")
                emit(Resource.Error("Error adding plant: $error"))
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

            val body = response.body()

            if (response.isSuccessful && body != null) {
                if (body.success && body.data != null) {
                    emit(Resource.Success(body.data.map { it.toGardenPlant() }))
                } else {
                    emit(Resource.Error(body.message.ifBlank { "No data received" }))
                }
            } else {
                val error = response.errorBody()?.string()
                emit(Resource.Error("Error fetching plants: ${error ?: response.message()}"))
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
            val body = response.body()

            if (response.isSuccessful && body != null) {
                if (body.success) {
                    emit(Resource.Success(Unit))
                } else {
                    emit(Resource.Error(body.message.ifBlank { "Delete failed" }))
                }
            } else {
                val error = response.errorBody()?.string()
                emit(Resource.Error("Error deleting plant: ${error ?: response.message()}"))
            }

        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }
}
