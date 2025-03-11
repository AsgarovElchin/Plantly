package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.CategoryDto
import asgarov.elchin.plantly.feature_explore.domain.model.Category

fun CategoryDto.toCategory():Category{
    return Category(
        categoryName = category
    )
}