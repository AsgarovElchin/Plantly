package asgarov.elchin.plantly.feature_explore.presentation

import asgarov.elchin.plantly.feature_explore.domain.model.Category

data class CategoryState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)



