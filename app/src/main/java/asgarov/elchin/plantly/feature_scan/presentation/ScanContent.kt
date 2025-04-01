package asgarov.elchin.plantly.feature_scan.presentation

import androidx.compose.runtime.Composable
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ContentCut
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.Opacity
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import java.io.File


@Composable
fun ScanContent(
    scanState: ScanState,
    onImageCaptured: (Uri) -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val photoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && imageUri != null) {
            onImageCaptured(imageUri!!)
        }
    }

    fun createImageUri(): Uri {
        val imageFile = File.createTempFile("plant_", ".jpg", context.cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )
    }

    LaunchedEffect(scanState.result) {
        if (scanState.result.isBlank() && !scanState.isLoading) {
            imageUri = createImageUri()
            photoLauncher.launch(imageUri!!)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            item {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            when {
                scanState.isLoading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(top = 80.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator()
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "Identifying plant, please wait…",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }

                scanState.result.contains("this is not a plant", ignoreCase = true) -> {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                        ) {
                            Text(
                                text = scanState.result,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }

                scanState.result.isNotBlank() -> {
                    val parsedMap = parsePlantResponseClean(scanState.result)
                    val uiList = mapToPlantInfoUi(parsedMap)

                    items(uiList) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(28.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = item.value.replace("*", "• "),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun parsePlantResponseClean(response: String): Map<String, String> {
    val labels = listOf(
        "Common Name",
        "Scientific Name",
        "Watering Instructions",
        "Sunlight Needs",
        "Pruning Advice"
    )

    val result = mutableMapOf<String, String>()

    for (i in labels.indices) {
        val currentLabel = labels[i]
        val regex = Regex("${Regex.escape(currentLabel)}\\s*:", RegexOption.IGNORE_CASE)
        val match = regex.find(response) ?: continue
        val startIndex = match.range.last + 1

        val endIndex = if (i < labels.lastIndex) {
            Regex("${Regex.escape(labels[i + 1])}\\s*:", RegexOption.IGNORE_CASE)
                .find(response)?.range?.first ?: response.length
        } else {
            response.length
        }

        val content = response.substring(startIndex, endIndex).trim().trim('.')
        if (content.isNotEmpty()) {
            result[currentLabel] = content
        }
    }

    return result
}

fun mapToPlantInfoUi(data: Map<String, String>): List<PlantInfoUi> {
    return data.map { (key, value) ->
        when (key.lowercase()) {
            "common name" -> PlantInfoUi(Icons.Outlined.LocalFlorist, "Common Name", value)
            "scientific name" -> PlantInfoUi(Icons.Outlined.Science, "Scientific Name", value)
            "watering instructions" -> PlantInfoUi(Icons.Outlined.Opacity, "Watering", value)
            "sunlight needs" -> PlantInfoUi(Icons.Outlined.WbSunny, "Sunlight", value)
            "pruning advice" -> PlantInfoUi(Icons.Outlined.ContentCut, "Pruning", value)
            else -> PlantInfoUi(Icons.Outlined.Info, key, value)
        }
    }
}
