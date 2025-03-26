package asgarov.elchin.plantly.feature_reminder.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import asgarov.elchin.plantly.feature_reminder.domain.model.ReminderType
import coil3.compose.rememberAsyncImagePainter
import java.time.format.DateTimeFormatter


@Composable
fun ReminderContent(
    reminders: List<Reminder>,
    onPlantClick: (Long, ReminderType) -> Unit,
    fetchPlantImage: (Long) -> Unit,
    plantImages: Map<Long, String>
) {
    val groupedByDateAndType = reminders.groupBy { it.nextReminderDateTime.toLocalDate() }
        .mapValues { it.value.groupBy { reminder -> reminder.reminderType } }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        groupedByDateAndType.forEach { (date, actionMap) ->
            item {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            actionMap.forEach { (type, remindersForType) ->
                item {
                    ReminderActionSection(
                        reminderType = type,
                        reminders = remindersForType,
                        onPlantClick = onPlantClick,
                        fetchPlantImage = fetchPlantImage,
                        plantImages = plantImages
                    )
                }
            }
        }
    }
}


@Composable
fun ReminderActionSection(
    reminderType: ReminderType,
    reminders: List<Reminder>,
    onPlantClick: (Long, ReminderType) -> Unit,
    fetchPlantImage: (Long) -> Unit,
    plantImages: Map<Long, String>
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { expanded = !expanded },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${reminderType.name.lowercase().replaceFirstChar { it.uppercase() }} (${reminders.size})",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            if (expanded) {
                reminders.forEach { reminder ->
                    LaunchedEffect(reminder.plantId) {
                        if (!plantImages.containsKey(reminder.plantId)) {
                            fetchPlantImage(reminder.plantId)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onPlantClick(reminder.id, reminderType)
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        androidx.compose.foundation.Image(
                            painter = rememberAsyncImagePainter(
                                model = plantImages[reminder.plantId] ?: ""
                            ),
                            contentDescription = reminder.plantName,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .weight(0.2f)
                        )

                        Column(modifier = Modifier.weight(0.8f)) {
                            Text(text = reminder.plantName)
                            Text(
                                text = reminder.nextReminderDateTime.toLocalTime()
                                    .format(DateTimeFormatter.ofPattern("HH:mm"))
                            )
                        }
                    }
                }
            }
        }
    }
}
