package asgarov.elchin.plantly.feature_my_garden.domain.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant
import kotlinx.coroutines.flow.Flow

interface GardenPlantRepository {

    fun addGardenPlant(gardenPlant: GardenPlant): Flow<Resource<GardenPlant>>

    fun getAllGardenPlants(): Flow<Resource<List<GardenPlant>>>

    fun deleteGardenPlant(id: Long): Flow<Resource<Unit>>

}