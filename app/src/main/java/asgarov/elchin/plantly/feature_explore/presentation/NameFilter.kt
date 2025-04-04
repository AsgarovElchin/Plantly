package asgarov.elchin.plantly.feature_explore.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun FilterBar(
    searchQuery: String,
    labelText: String = "Search",
    onSearchQueryChange: (String?) -> Unit,
    debounceTimeMillis: Long = 700
) {
    var query by remember { mutableStateOf(searchQuery) }
    val coroutineScope = rememberCoroutineScope()
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    var isFocused by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { newText ->
                query = newText
                debounceJob?.cancel()
                debounceJob = debounce(coroutineScope, debounceTimeMillis) {
                    onSearchQueryChange(newText.takeIf { it.isNotBlank() })
                }
            },
            label = { Text(labelText) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                if (query.isBlank()){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

private fun debounce(
    scope: CoroutineScope,
    delayMillis: Long,
    action: () -> Unit
): Job {
    return scope.launch {
        delay(delayMillis)
        action()
    }
}