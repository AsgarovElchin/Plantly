package asgarov.elchin.plantly.feature_reminder.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SetReminderScreen() {
    val viewModel: ReminderViewModel = hiltViewModel()
    val reminderState = viewModel.reminderState.value

    Box(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
        reminderState.reminder?.let { reminder ->
            SetReminderContent()

            if (reminderState.error.isNotBlank()) {
                Text(
                    text = reminderState.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center)
                )
            }

            if (reminderState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } ?: run {
            Text(
                text = "Reminder not found.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}