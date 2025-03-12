package asgarov.elchin.plantly.feature_explore.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import asgarov.elchin.plantly.BuildConfig
import asgarov.elchin.plantly.feature_explore.data.paging.PlantPagingSource
import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantRepository
import asgarov.elchin.plantly.feature_explore.domain.use_case.PlantFilter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val api: PlantsDataApi
) : PlantRepository {

    override fun getAllPlants(filter: PlantFilter): Flow<PagingData<Plant>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PlantPagingSource(api, BuildConfig.API_KEY, filter)
            }
        ).flow
    }
}