package asgarov.elchin.plantly.feature_explore.domain.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData
import kotlinx.coroutines.flow.Flow

interface PlantCareRepository {

     fun getPlantCareGuidesById(speciesId: Int): Flow<Resource<List<PlantCareGuideData>>>
}