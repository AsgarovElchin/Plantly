package asgarov.elchin.plantly.feature_reminder.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import asgarov.elchin.plantly.feature_reminder.domain.model.ReminderType

@Composable
fun SetReminderContent(
    reminder: Reminder,
    onSave: (Reminder) -> Unit
) {
    var selectedAction by remember { mutableStateOf(reminder.reminderType.name.lowercase().replaceFirstChar { it.uppercase() }) }
    var selectedNumber by remember { mutableStateOf(reminder.repeatEvery) }
    var selectedUnit by remember { mutableStateOf(reminder.repeatUnit) }
    var selectedHour by remember { mutableStateOf(reminder.reminderTime.hour % 12) }
    var selectedMinute by remember { mutableStateOf(reminder.reminderTime.minute) }
    var isAM by remember { mutableStateOf(reminder.reminderTime.hour < 12) }

    Column(modifier = Modifier.fillMaxSize()) {
        PlantHeader(plantName = reminder.plantName)

        ExpandableSection(
            title = "Remind me about",
            summary = {
                Text(
                    text = selectedAction,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        ) {
            ActionPickerUI(selectedAction) { selectedAction = it }
        }

        ExpandableSection(
            title = "Repeat Interval",
            summary = {
                Text(
                    text = "Every $selectedNumber $selectedUnit",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        ) {
            RepeatPickerUI(
                selectedNumber,
                selectedUnit,
                onNumberChange = { selectedNumber = it },
                onUnitChange = { selectedUnit = it }
            )
        }

        ExpandableSection(
            title = "Select Time",
            summary = {
                val period = if (isAM) "AM" else "PM"
                Text(
                    text = "${selectedHour.toString().padStart(2, '0')}:${selectedMinute.toString().padStart(2, '0')} $period",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        ) {
            TimePickerUI(
                selectedHour,
                selectedMinute,
                isAM,
                onHourChange = { selectedHour = it },
                onMinuteChange = { selectedMinute = it },
                onPeriodChange = { isAM = it }
            )
        }

        TurnOnReminderButton(onClick = {
            val finalHour = if (isAM) selectedHour else selectedHour + 12
            val updatedReminder = reminder.copy(
                reminderType = ReminderType.valueOf(selectedAction.uppercase()),
                repeatEvery = selectedNumber,
                repeatUnit = selectedUnit,
                reminderTime = reminder.reminderTime.withHour(finalHour).withMinute(selectedMinute)
            )
            onSave(updatedReminder)
        })

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
        androidx.compose.material3.Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Turn On Reminder",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
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
            color = Color(0xFF1B5E20)
        )
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
        colors = CardDefaults.cardColors(containerColor = Color.White)
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
                color = Color.Black,
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
fun ActionPickerUI(selectedAction: String, onActionSelected: (String) -> Unit) {
    val actions = listOf("Misting", "Watering", "Rotating", "Fertilizing")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Remind me ${selectedAction.lowercase()}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
        Spacer(modifier = Modifier.height(16.dp))

        val listState = rememberLazyListState()
        val flingBehavior = rememberSnapFlingBehavior(listState)

        LazyColumn(
            state = listState,
            modifier = Modifier
                .width(120.dp)
                .height(90.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            flingBehavior = flingBehavior
        ) {
            items(actions) { action ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(
                            if (action == selectedAction) Color.LightGray.copy(alpha = 0.2f)
                            else Color.Transparent
                        )
                        .clickable { onActionSelected(action) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = action,
                        fontSize = if (action == selectedAction) 20.sp else 16.sp,
                        fontWeight = if (action == selectedAction) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun RepeatPickerUI(
    selectedNumber: Int,
    selectedUnit: String,
    onNumberChange: (Int) -> Unit,
    onUnitChange: (String) -> Unit
) {
    val repeatUnits = listOf("Days", "Weeks", "Months")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Every $selectedNumber $selectedUnit",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            PickerColumn((1..12).toList(), selectedNumber, onNumberChange)
            Spacer(modifier = Modifier.width(16.dp))
            PickerColumn(repeatUnits, selectedUnit, onUnitChange)
        }
    }
}

@Composable
fun TimePickerUI(
    selectedHour: Int,
    selectedMinute: Int,
    isAM: Boolean,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onPeriodChange: (Boolean) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${selectedHour.toString().padStart(2, '0')}:${selectedMinute.toString().padStart(2, '0')}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )
            Spacer(modifier = Modifier.width(16.dp))
            ToggleButton("AM", isAM) { onPeriodChange(true) }
            Spacer(modifier = Modifier.width(16.dp))
            ToggleButton("PM", !isAM) { onPeriodChange(false) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            TimeColumn((1..12).toList(), selectedHour, onHourChange)
            Spacer(modifier = Modifier.width(16.dp))
            TimeColumn((0..59).toList(), selectedMinute, onMinuteChange)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun <T> PickerColumn(
    values: List<T>,
    selectedValue: T,
    onValueChange: (T) -> Unit
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = values.indexOf(selectedValue)
    )
    val flingBehavior = rememberSnapFlingBehavior(listState)

    LazyColumn(
        state = listState,
        modifier = Modifier
            .width(80.dp)
            .height(90.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        flingBehavior = flingBehavior
    ) {
        items(values) { value ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(if (value == selectedValue) Color.LightGray.copy(alpha = 0.2f) else Color.Transparent)
                    .clickable { onValueChange(value) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value.toString(),
                    fontSize = if (value == selectedValue) 20.sp else 16.sp,
                    fontWeight = if (value == selectedValue) FontWeight.Bold else FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
    }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        val centerIndex = listState.firstVisibleItemIndex + 1
        if (centerIndex in values.indices) {
            onValueChange(values[centerIndex])
        }
    }
}

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun TimeColumn(
    values: List<Int>,
    selectedValue: Int,
    onValueChange: (Int) -> Unit
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = maxOf(values.indexOf(selectedValue) - 1, 0)
    )
    val flingBehavior = rememberSnapFlingBehavior(listState)

    LazyColumn(
        state = listState,
        modifier = Modifier
            .width(60.dp)
            .height(90.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        flingBehavior = flingBehavior
    ) {
        items(values) { value ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(if (value == selectedValue) Color.LightGray.copy(alpha = 0.2f) else Color.Transparent)
                    .clickable { onValueChange(value) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value.toString().padStart(2, '0'),
                    fontSize = if (value == selectedValue) 20.sp else 16.sp,
                    fontWeight = if (value == selectedValue) FontWeight.Bold else FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
    }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        val centerIndex = listState.firstVisibleItemIndex + 1
        if (centerIndex in values.indices) {
            onValueChange(values[centerIndex])
        }
    }
}

@Composable
fun ToggleButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) Color(0xFF1B5E20) else Color.Transparent)
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color(0xFF1B5E20),
            fontWeight = FontWeight.Bold
        )
    }
}
