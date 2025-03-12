package asgarov.elchin.plantly.feature_explore.domain.use_case

import androidx.paging.PagingData
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPlantsUseCase @Inject constructor(
    private val plantRepository: PlantRepository
) {

    operator fun invoke(filter: PlantFilter): Flow<PagingData<Plant>> {
        return plantRepository.getAllPlants(filter)
    }

}