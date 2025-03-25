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

@Composable
fun EditReminderScreen(navController: NavController) {
    val viewModel: EditReminderViewModel = hiltViewModel()
    val state = viewModel.reminderState.value

    if (state.successMessage.isNotBlank()) {

        LaunchedEffect(Unit) {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("refresh_reminders", true)
            navController.popBackStack()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        state.reminder?.let { reminder ->
            SetReminderContent(
                reminder = reminder,
                onSave = { updatedReminder ->
                    viewModel.updateReminder(reminder.id, updatedReminder)
                }
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.reminder == null && !state.isLoading && state.error.isBlank()) {
            Text(
                text = "Reminder not found.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
