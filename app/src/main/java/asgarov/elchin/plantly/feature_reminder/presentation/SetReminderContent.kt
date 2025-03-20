package asgarov.elchin.plantly.feature_reminder.presentation

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SetReminderContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        ExpandableSection(title = "Remind me about") { ActionPickerUI() }
        ExpandableSection(title = "Repeat Interval") { RepeatPickerUI() }
        ExpandableSection(title = "Select Time") { TimePickerUI() }
    }
}

@Composable
fun ExpandableSection(title: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp).clickable { expanded = !expanded },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = title,
                fontSize = 18.sp,
                color = Color.Black
            )
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                content()
            }
        }
    }
}

@Composable
fun ActionPickerUI() {
    var selectedAction by remember { mutableStateOf("") }
    val repeatActions = listOf("Misting", "Watering", "Rotating", "Misting")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Remind me ${selectedAction.lowercase()}", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
        Spacer(modifier = Modifier.height(16.dp))
        val listState = rememberLazyListState()
        val flingBehavior = rememberSnapFlingBehavior(listState)
        LazyColumn(
            state = listState,
            modifier = Modifier.width(120.dp).height(90.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            flingBehavior = flingBehavior
        ) {
            items(repeatActions) { action ->
                Box(
                    modifier = Modifier.fillMaxWidth().height(30.dp)
                        .background(if (action == selectedAction) Color.LightGray.copy(alpha = 0.2f) else Color.Transparent)
                        .clickable { selectedAction = action },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = action, fontSize = if (action == selectedAction) 20.sp else 16.sp,
                        fontWeight = if (action == selectedAction) FontWeight.Bold else FontWeight.Normal)
                }
            }
        }
    }
}

@Composable
fun RepeatPickerUI() {
    var selectedNumber by remember { mutableStateOf(1) }
    var selectedUnit by remember { mutableStateOf("Days") }
    val repeatUnits = listOf("Days", "Weeks", "Months")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Every $selectedNumber $selectedUnit", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            PickerColumn((1..12).toList(), selectedNumber, { selectedNumber = it })
            Spacer(modifier = Modifier.width(16.dp))
            PickerColumn(repeatUnits, selectedUnit, { selectedUnit = it })
        }
    }
}

@Composable
fun TimePickerUI() {
    var selectedHour by remember { mutableStateOf(11) }
    var selectedMinute by remember { mutableStateOf(20) }
    var isAM by remember { mutableStateOf(true) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "$selectedHour:${selectedMinute.toString().padStart(2, '0')}", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
            Spacer(modifier = Modifier.width(16.dp))
            ToggleButton("AM", isAM) { isAM = true }
            Spacer(modifier = Modifier.width(16.dp))
            ToggleButton("PM", !isAM) { isAM = false }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            TimeColumn((1..12).toList(), selectedHour, { selectedHour = it })
            Spacer(modifier = Modifier.width(16.dp))
            TimeColumn((0..59).toList(), selectedMinute, { selectedMinute = it })

        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun <T> PickerColumn(values: List<T>, selectedValue: T, onValueChange: (T) -> Unit) {
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

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun TimeColumn(values: List<Int>, selectedValue: Int, onValueChange: (Int) -> Unit) {
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
                    .clickable {
                        onValueChange(value)
                    },
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

