package asgarov.elchin.plantly.feature_explore.presentation.screen.plant

import asgarov.elchin.plantly.feature_explore.domain.model.Plant

data class PlantState(
    val isLoading: Boolean = false,
    val plants: List<Plant> = emptyList(),
    val error: String = ""
)
