package asgarov.elchin.plantly.feature_reminder.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asgarov.elchin.plantly.feature_reminder.domain.model.PreviousData
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import asgarov.elchin.plantly.feature_reminder.domain.model.ReminderType
import asgarov.elchin.plantly.ui.theme.*
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults

@Composable
fun SetReminderContent(
    reminder: Reminder,
    onSave: (Reminder) -> Unit
) {
    var selectedAction by remember { mutableStateOf(reminder.reminderType.name.lowercase().replaceFirstChar { it.uppercase() }) }
    var selectedNumber by remember { mutableStateOf(reminder.repeatEvery) }
    var selectedUnit by remember { mutableStateOf(reminder.repeatUnit) }
    var selectedTime by remember { mutableStateOf(reminder.reminderTime.toLocalTime()) }
    var selectedPreviousData by remember { mutableStateOf(reminder.previousData) }

    val selectorProps = WheelPickerDefaults.selectorProperties(
        enabled = true,
        shape = RoundedCornerShape(12.dp),
        color = primaryLight.copy(alpha = 0.15f),
        border = BorderStroke(1.dp, primaryLight)
    )

    Column(modifier = Modifier.fillMaxSize().verticalScroll(
        rememberScrollState()
    )) {
        PlantHeader(plantName = reminder.plantName)

        ExpandableSection(
            title = "Remind me about",
            summary = {
                Text(
                    text = selectedAction,
                    color = onSurfaceVariantLight,
                    fontSize = 16.sp
                )
            }
        ) {
            DropdownMenuPicker(
                options = listOf("Watering", "Misting", "Rotating", "Fertilizing"),
                selected = selectedAction,
                onSelectedChange = { selectedAction = it }
            )
        }

        ExpandableSection(
            title = "Repeat Interval",
            summary = {
                Text(
                    text = "Every $selectedNumber $selectedUnit",
                    color = onSurfaceVariantLight,
                    fontSize = 16.sp
                )
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DropdownMenuPicker(
                    options = (1..12).map { it.toString() },
                    selected = selectedNumber.toString(),
                    onSelectedChange = { selectedNumber = it.toInt() }
                )
                DropdownMenuPicker(
                    options = listOf("Days", "Weeks", "Months"),
                    selected = selectedUnit,
                    onSelectedChange = { selectedUnit = it }
                )
            }
        }

        ExpandableSection(
            title = "Select Time",
            summary = {
                val hour = selectedTime.hour % 12
                val minute = selectedTime.minute
                val amPm = if (selectedTime.hour < 12) "AM" else "PM"
                Text(
                    text = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')} $amPm",
                    color = onSurfaceVariantLight,
                    fontSize = 16.sp
                )
            }
        ) {
            WheelTimePicker(
                startTime = selectedTime,
                timeFormat = TimeFormat.AM_PM,
                selectorProperties = selectorProps,
                onSnappedTime = { snappedTime ->
                    selectedTime = snappedTime
                }
            )
        }

        ExpandableSection(
            title = "Last Done",
            summary = {
                Text(
                    text = selectedPreviousData.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() },
                    color = onSurfaceVariantLight,
                    fontSize = 16.sp
                )
            }
        ) {
            DropdownMenuPicker(
                options = PreviousData.values().map { it.name.replace("_", " ").lowercase().replaceFirstChar { c -> c.uppercase() } },
                selected = selectedPreviousData.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() },
                onSelectedChange = {
                    selectedPreviousData = PreviousData.valueOf(it.uppercase().replace(" ", "_"))
                }
            )
        }

        TurnOnReminderButton(onClick = {
            val updatedReminder = reminder.copy(
                reminderType = ReminderType.valueOf(selectedAction.uppercase()),
                repeatEvery = selectedNumber,
                repeatUnit = selectedUnit,
                reminderTime = reminder.reminderTime.withHour(selectedTime.hour).withMinute(selectedTime.minute),
                previousData = selectedPreviousData
            )
            onSave(updatedReminder)
        })
    }
}

@Composable
fun DropdownMenuPicker(options: List<String>, selected: String, onSelectedChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .padding(8.dp)
        .clickable { expanded = true }
        .border(BorderStroke(1.dp, primaryLight), RoundedCornerShape(8.dp))
        .padding(12.dp)) {
        Text(text = selected, color = onSurfaceLight)
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelectedChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ExpandableSection(
    title: String,
    summary: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceContainerLowLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = onSurfaceLight,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    content()
                }
            } else {
                summary()
            }
        }
    }
}

@Composable
fun PlantHeader(plantName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = plantName,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = primaryLight
        )
    }
}

@Composable
fun TurnOnReminderButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryLight)
        ) {
            Text(
                text = "Turn On Reminder",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = onPrimaryLight
            )
        }
    }
}
