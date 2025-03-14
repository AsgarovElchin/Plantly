package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import asgarov.elchin.plantly.feature_explore.domain.model.PlantDetail
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import asgarov.elchin.plantly.feature_explore.domain.model.PlantCareGuideData
import asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.component.CharacteristicSection
import asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail.component.PlantCareSection
import coil3.request.crossfade

@Composable
fun PlantDetailContent(plant: PlantDetail,  plantCareGuideData: List<PlantCareGuideData>?,) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        PlantImageHeader(imageUrl = plant.defaultImage?.regularUrl, scrollState)
        PlantInfoContent(plantCareGuideData, plant, scrollState)
    }
}

@Composable
fun PlantImageHeader(imageUrl: String?, scrollState: ScrollState) {
    val blurRadius by animateFloatAsState(targetValue = if (scrollState.value > 50) 10f else 0f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .blur(blurRadius.dp)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Plant Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            loading = {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            },
            error = {
                Box(
                    modifier = Modifier.fillMaxSize().background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.ImageNotSupported, contentDescription = "Image not available", tint = Color.White)
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))))
        )
        Text(
            text = imageUrl?.let { "Plant Overview" } ?: "No Image",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun PlantInfoContent(
    plantCareGuideData: List<PlantCareGuideData>?,
    plant: PlantDetail,
    scrollState: ScrollState
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 250.dp)
            .verticalScroll(scrollState),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = plant.commonName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = plant.scientificName.joinToString(", "),
                fontSize = 18.sp,
                color = Color.Gray,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = plant.description,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))

            CharacteristicSection(plant)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Plant Care Guide",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            plantCareGuideData?.flatMap { it.careSections }?.let { careSections ->
                if (careSections.isNotEmpty()) {
                    PlantCareSection(careSections)
                } else {
                    Text(
                        text = "No care information available",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

        }
    }
}}




