package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail

import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail

data class PlantDetailState(
    val plantDetail: PlantDetail? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)

