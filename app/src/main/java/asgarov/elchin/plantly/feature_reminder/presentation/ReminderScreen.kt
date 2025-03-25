package asgarov.elchin.plantly.feature_reminder.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ReminderScreen(
    navController: NavController,
) {
    val viewModel: ReminderViewModel = hiltViewModel()
    val state = viewModel.reminderListState.value

    LaunchedEffect(key1 = navController) {
        navController.currentBackStackEntryFlow.collectLatest { entry ->
            val shouldRefresh = entry.savedStateHandle.get<Boolean>("refresh_reminders") ?: false
            if (shouldRefresh) {
                viewModel.getAllReminders()
                entry.savedStateHandle.set("refresh_reminders", false)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center)
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
                    }
                )
            }

            else -> {
                Text(
                    text = "No reminders found.",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
