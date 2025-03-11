package asgarov.elchin.plantly.feature_explore.domain.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

     fun getAllPlantsByCategory(category: String): Flow<Resource<List<Plant>>>

}