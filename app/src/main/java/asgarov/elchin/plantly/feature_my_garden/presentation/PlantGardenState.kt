package asgarov.elchin.plantly.feature_my_garden.presentation

import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant

data class PlantGardenState(
    val plantGarden: GardenPlant? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
