package asgarov.elchin.plantly.feature_my_garden.presentation

import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant

data class PlantListGardenState(
        val plantGarden: List<GardenPlant>? = null,
        val isLoading: Boolean = false,
        val error: String = ""
)
