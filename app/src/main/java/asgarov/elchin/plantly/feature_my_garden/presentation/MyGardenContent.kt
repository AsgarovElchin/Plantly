package asgarov.elchin.plantly.feature_my_garden.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder

@Composable
fun MyGardenContent(
    gardenPlants: List<GardenPlant>,
    onItemClick: (GardenPlant) -> Unit,
    onDeleteClick: (Long, Long) -> Unit,
    onAddReminderClick: (GardenPlant) -> Unit,
    reminders: List<Reminder>,
    onEditReminderClick: (Long, String) -> Unit
){

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(gardenPlants) { plant ->
            GardenPlantItem(plant, onItemClick, onDeleteClick, onAddReminderClick, reminders, onEditReminderClick)
        }
    }


}