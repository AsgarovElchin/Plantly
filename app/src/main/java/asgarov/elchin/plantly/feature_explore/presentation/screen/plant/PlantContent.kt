package asgarov.elchin.plantly.feature_explore.presentation.screen.plant

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import asgarov.elchin.plantly.feature_explore.presentation.screen.plant.component.PlantItem

@Composable
fun PlantContent(
    plants: List<Plant>,
    onPlantClick: (Plant) -> Unit
){
    LazyColumn(
        modifier = Modifier.padding(12.dp)
    ) {
        items(plants) { plant ->
            PlantItem(
                plant = plant,
                onPlantClick = onPlantClick
            )
        }
    }

}