package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import asgarov.elchin.plantly.core.navigation.NavigationRoute
import asgarov.elchin.plantly.feature_my_garden.presentation.GardenPlantViewModel
import kotlinx.coroutines.launch

@Composable
fun PlantDetailScreen(navController: NavController) {
    val plantViewModel: PlantDetailViewModel = hiltViewModel()
    val gardenViewModel: GardenPlantViewModel = hiltViewModel()

    val plantDetailState = plantViewModel.plantDetailState.value
    val plantCareState = plantViewModel.plantCareState.value
    val plantGardenState = plantViewModel.plantGardenState.value
    val gardenPlantsState = gardenViewModel.plantListGardenState.value

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        val plantCareGuideData = plantCareState.plantCare

        plantDetailState.plantDetail?.let { plant ->
            val isAlreadyInGarden = gardenPlantsState.plantGarden?.any { it.id == plant.id.toLong() } == true

            PlantDetailContent(
                plant = plant,
                plantCareGuideData = plantCareGuideData,
                isPlantAlreadyInGarden = isAlreadyInGarden,
                onAddToGardenClick = {
                    plantViewModel.addPlantToGarden(plant)
                    true
                },
                onNavigateToGarden = {
                    coroutineScope.launch {
                        navController.navigate(NavigationRoute.MyGarden.route) {
                            popUpTo(NavigationRoute.ExploreRoute.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }

        LaunchedEffect(plantGardenState.plantGarden, plantGardenState.error) {
            if (plantGardenState.plantGarden != null) {
                coroutineScope.launch {
                    snackBarHostState.showSnackbar("Plant added to My Garden!")
                }
            } else if (plantGardenState.error.isNotBlank()) {
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(plantGardenState.error)
                }
            }
        }

        SnackbarHost(hostState = snackBarHostState)

        if (plantDetailState.error.isNotBlank()) {
            Text(
                text = plantDetailState.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (plantDetailState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}