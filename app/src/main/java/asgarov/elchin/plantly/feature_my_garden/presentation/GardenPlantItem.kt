package asgarov.elchin.plantly.feature_my_garden.presentation

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import asgarov.elchin.plantly.feature_my_garden.domain.model.GardenPlant
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import coil3.compose.SubcomposeAsyncImage

@Composable
fun GardenPlantItem(
    gardenPlant: GardenPlant,
    onItemClick: (GardenPlant) -> Unit,
    onDeleteClick: (Long, Long) -> Unit,
    onAddReminderClick: (GardenPlant) -> Unit,
    reminders: List<Reminder>,
    onEditReminderClick: (Long, String) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth().height(440.dp)
            .clickable { onItemClick(gardenPlant) }
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = gardenPlant.defaultImage?.originalUrl,
                contentDescription = "Plant's image",
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            strokeWidth = 2.dp
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = gardenPlant.commonName,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = gardenPlant.scientificNames.joinToString(", ") ?: "Unknown",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))

                val remindersForPlant = reminders.filter { it.plantId == gardenPlant.id }

                if(remindersForPlant.isNotEmpty()){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        remindersForPlant.forEach { reminder ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                IconButton(
                                    onClick = {
                                        onEditReminderClick(reminder.id, reminder.reminderType.name)
                                    },
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Icon(
                                        imageVector = when (reminder.reminderType.name) {
                                            "MISTING" -> Icons.Default.Air
                                            "WATERING" -> Icons.Default.Opacity
                                            "ROTATING" -> Icons.Default.ScreenRotation
                                            "FERTILIZING" -> Icons.Default.Eco
                                            else -> Icons.Default.Info
                                        },
                                        contentDescription = reminder.reminderType.name,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Text(
                                    text = reminder.reminderType.name.lowercase()
                                        .replaceFirstChar { it.uppercase() },
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }

                }

                Button(
                        onClick = { onAddReminderClick(gardenPlant) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Add Reminder")
                    }
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                        onClick = { onDeleteClick(gardenPlant.id, reminders.find { it.plantId == gardenPlant.id }?.id ?: -1) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                    ) {
                        Text(text = "Delete", color = MaterialTheme.colorScheme.onError)
                    }

                }
            }


        }


    }