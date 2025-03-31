package asgarov.elchin.plantly.feature_reminder.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.plantly.core.navigation.NavigationRoute
import asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.PlantDetailViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ReminderScreen(
    navController: NavController,
) {
    val reminderViewModel: ReminderViewModel = hiltViewModel()
    val plantDetailViewModel: PlantDetailViewModel = hiltViewModel()

    val state = reminderViewModel.reminderListState.value
    val plantImages = plantDetailViewModel.plantImageMap.value

    LaunchedEffect(Unit) {
        reminderViewModel.getAllReminders()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        when {
            state.isLoading -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Loading reminders...",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            state.reminders != null -> {
                ReminderContent(
                    reminders = state.reminders,
                    onPlantClick = { reminderId, reminderType ->
                        val route = NavigationRoute.EditReminderRoute.createRoute(
                            reminderId = reminderId,
                            reminderType = reminderType.name
                        )
                        navController.navigate(route)
                    },
                    fetchPlantImage = { plantId ->
                        plantDetailViewModel.getPlantDetailsById(plantId.toInt())
                    },
                    plantImages = plantImages
                )
            }

            else -> {
                Text(
                    text = "No reminders found.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

