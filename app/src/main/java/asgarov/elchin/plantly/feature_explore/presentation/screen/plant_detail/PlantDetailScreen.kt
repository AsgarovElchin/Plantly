package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment

@Composable
fun PlantDetailScreen() {
    val plantDetailViewModel: PlantDetailViewModel = hiltViewModel()
    val plantDetailState = plantDetailViewModel.plantDetailState.value
    val plantCareState = plantDetailViewModel.plantCareState.value

    Box(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
        val plantCareGuideData = plantCareState.plantCare

        plantDetailState.plantDetail?.let { plant ->
            PlantDetailContent(plant = plant, plantCareGuideData = plantCareGuideData)
        }

        if (plantDetailState.error.isNotBlank()) {
            Text(
                text = plantDetailState.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (plantDetailState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}