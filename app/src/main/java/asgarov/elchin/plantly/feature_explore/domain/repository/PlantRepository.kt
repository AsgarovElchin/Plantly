package asgarov.elchin.plantly.feature_explore.domain.repository

import androidx.paging.PagingData
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import asgarov.elchin.plantly.feature_explore.domain.use_case.PlantFilter
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    fun getAllPlants(filter: PlantFilter): Flow<PagingData<Plant>>
}