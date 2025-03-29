package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail
import asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.component.CharacteristicSection
import asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.component.PlantCareSection
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlantDetailContent(
    plant: PlantDetail,
    plantCareGuideData: List<PlantCareGuideData>?,
    isPlantAlreadyInGarden: Boolean,
    onAddToGardenClick: suspend () -> Boolean,
    onNavigateToGarden: () -> Unit
) {
    val scrollState = rememberScrollState()
    var isAdded by remember { mutableStateOf(isPlantAlreadyInGarden) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(plant.defaultImage.regularUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Plant Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(MaterialTheme.shapes.medium),
            loading = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ImageNotSupported,
                        contentDescription = "Image not available",
                        tint = Color.White
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = plant.commonName,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = plant.scientificName.joinToString(", "),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(12.dp))

        var expanded by remember { mutableStateOf(false) }

        Text(
            text = if (expanded) plant.description else plant.description.take(300) + "...",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )

        Text(
            text = if (expanded) "Show Less" else "Read More",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                coroutineScope.launch {
                    isLoading = true
                    val result = onAddToGardenClick()
                    delay(500) // Simulate saving process
                    isAdded = result
                    onNavigateToGarden()
                }
            },
            enabled = !isAdded && !isLoading,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(36.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Adding...")
                    }
                    isAdded -> {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Already in Garden")
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Add to Garden")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        CharacteristicSection(plant)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Plant Care Guide",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 20.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        plantCareGuideData?.flatMap { it.careSections }?.let { careSections ->
            AnimatedVisibility(
                visible = careSections.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                PlantCareSection(careSections)
            }
        } ?: Text(
            text = "No care information available",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}
