package asgarov.elchin.plantly.feature_explore.domain.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

      fun getAllCategories(): Flow<Resource<List<Category>>>

}