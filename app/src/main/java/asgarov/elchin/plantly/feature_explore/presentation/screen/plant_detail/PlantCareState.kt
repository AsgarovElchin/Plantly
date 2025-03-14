package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail

import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData

data class PlantCareState(
    val plantCare: List<PlantCareGuideData>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

