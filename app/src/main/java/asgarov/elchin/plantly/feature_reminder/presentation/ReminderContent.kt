package asgarov.elchin.plantly.feature_reminder.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.R
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import asgarov.elchin.plantly.feature_reminder.domain.model.ReminderType
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import java.time.format.DateTimeFormatter


@Composable
fun ReminderContent(
    reminders: List<Reminder>,
    onPlantClick: (Long, ReminderType) -> Unit,
    fetchPlantImage: (Long) -> Unit,
    plantImages: Map<Long, String>
) {
    val grouped = reminders.groupBy { it.nextReminderDateTime.toLocalDate() }
        .mapValues { it.value.groupBy { r -> r.reminderType } }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        grouped.forEach { (date, typeMap) ->
            item {
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            typeMap.forEach { (type, reminderList) ->
                item {
                    ReminderActionSection(
                        reminderType = type,
                        reminders = reminderList,
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
    var expanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${reminderType.name.lowercase().replaceFirstChar { it.uppercase() }} (${reminders.size})",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (expanded) "−" else "+",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (expanded) {
                reminders.forEachIndexed { index, reminder ->
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val imageModel = plantImages[reminder.plantId]
                        var isImageLoading by remember { mutableStateOf(true) }

                        Box(
                            modifier = Modifier
                                .weight(0.2f)
                                .aspectRatio(1f)
                                .padding(end = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = imageModel,
                                contentDescription = reminder.plantName,
                                modifier = Modifier.fillMaxSize(),
                                onState = { state ->
                                    isImageLoading = state is AsyncImagePainter.State.Loading
                                }
                            )

                            if (isImageLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.fillMaxSize(0.4f),
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(0.8f)) {
                            Text(
                                text = reminder.plantName,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = reminder.nextReminderDateTime.toLocalTime()
                                    .format(DateTimeFormatter.ofPattern("HH:mm")),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (index != reminders.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun EmptyRemindersState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.dracaena),
            contentDescription = "No reminders",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No reminders yet",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Tap on a plant to set up your first reminder!",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
