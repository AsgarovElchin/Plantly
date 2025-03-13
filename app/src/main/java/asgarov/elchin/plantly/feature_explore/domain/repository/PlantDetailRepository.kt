package asgarov.elchin.plantly.feature_explore.domain.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail
import kotlinx.coroutines.flow.Flow

interface PlantDetailRepository{

     fun getPlantDetailsById(id: Int): Flow<Resource<PlantDetail>>

}