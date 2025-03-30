package asgarov.elchin.plantly.feature_my_garden.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Opacity
import androidx.compose.material.icons.outlined.ScreenRotation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import coil3.compose.SubcomposeAsyncImage
import androidx.compose.ui.layout.ContentScale

@Composable
fun GardenPlantItem(
    gardenPlant: GardenPlant,
    onItemClick: (GardenPlant) -> Unit,
    onDeleteClick: (Long, Long) -> Unit,
    onAddReminderClick: (GardenPlant) -> Unit,
    reminders: List<Reminder>,
    onEditReminderClick: (Long, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(gardenPlant) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column {
            SubcomposeAsyncImage(
                model = gardenPlant.defaultImage?.originalUrl,
                contentDescription = "Plant image",
                modifier = Modifier.fillMaxWidth().height(180.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = gardenPlant.commonName,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = gardenPlant.scientificNames.joinToString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                val remindersForPlant = reminders.filter { it.plantId == gardenPlant.id }

                AnimatedVisibility(visible = remindersForPlant.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    ) {
                        remindersForPlant.forEach { reminder ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(
                                    onClick = { onEditReminderClick(reminder.id, reminder.reminderType.name) },
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Icon(
                                        imageVector = getReminderIcon(reminder.reminderType.name),
                                        contentDescription = reminder.reminderType.name,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Text(
                                    text = reminder.reminderType.name.lowercase().replaceFirstChar { it.uppercase() },
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onAddReminderClick(gardenPlant) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddAlert,
                            contentDescription = "Add Reminder",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(
                        onClick = {
                            onDeleteClick(
                                gardenPlant.id,
                                reminders.find { it.plantId == gardenPlant.id }?.id ?: -1
                            )
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete Plant",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

private fun getReminderIcon(type: String): ImageVector {
    return when (type) {
        "MISTING" -> Icons.Outlined.Air
        "WATERING" -> Icons.Outlined.Opacity
        "ROTATING" -> Icons.Outlined.ScreenRotation
        "FERTILIZING" -> Icons.Outlined.Eco
        else -> Icons.Outlined.Info
    }
}
