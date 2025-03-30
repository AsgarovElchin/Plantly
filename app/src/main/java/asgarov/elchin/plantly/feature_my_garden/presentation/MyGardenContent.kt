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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import asgarov.elchin.plantly.R
import androidx.compose.foundation.Image

@Composable
fun MyGardenContent(
    gardenPlants: List<GardenPlant>,
    onItemClick: (GardenPlant) -> Unit,
    onDeleteClick: (Long, Long) -> Unit,
    onAddReminderClick: (GardenPlant) -> Unit,
    reminders: List<Reminder>,
    onEditReminderClick: (Long, String) -> Unit
) {
    if (gardenPlants.isEmpty()) {
        EmptyGardenState()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(gardenPlants) { plant ->
                GardenPlantItem(
                    plant, onItemClick, onDeleteClick,
                    onAddReminderClick, reminders, onEditReminderClick
                )
            }
        }
    }
}

@Composable
fun EmptyGardenState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.dracaena),
            contentDescription = "No plants",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No plants in your garden",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Start adding your favorite green friends!",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}