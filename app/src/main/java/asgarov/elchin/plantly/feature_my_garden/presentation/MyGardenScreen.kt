package asgarov.elchin.plantly.feature_my_garden.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.plantly.core.navigation.NavigationRoute
import asgarov.elchin.plantly.feature_reminder.presentation.ReminderViewModel

@Composable
fun MyGardenScreen(navController: NavController) {
    val gardenPlantViewModel: GardenPlantViewModel = hiltViewModel()
    val plantListGarden = gardenPlantViewModel.plantListGardenState.value

    val reminderViewModel: ReminderViewModel = hiltViewModel()
    val reminderListState = reminderViewModel.reminderListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        val gardenPlants = plantListGarden.plantGarden
        val reminders = reminderListState.reminders

        if (gardenPlants != null && reminders != null) {
            MyGardenContent(
                gardenPlants = gardenPlants,
                reminders = reminders,
                onItemClick = { plant ->
                    navController.navigate("plant_detail/${plant.id}")
                },
                onDeleteClick = { plantId, reminderId ->
                    gardenPlantViewModel.deleteGardenPlant(plantId)
                    reminderViewModel.deleteReminderByPlant(reminderId)
                },
                onAddReminderClick = { gardenPlant ->
                    navController.navigate(
                        NavigationRoute.SetReminderRoute.createRoute(
                            gardenPlant.id,
                            gardenPlant.commonName
                        )
                    )
                },
                onEditReminderClick = { reminderId, reminderType ->
                    navController.navigate(
                        NavigationRoute.EditReminderRoute.createRoute(
                            reminderId,
                            reminderType
                        )
                    )
                }
            )
        }

        if (plantListGarden.error.isNotBlank()) {
            Text(
                text = plantListGarden.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (plantListGarden.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}