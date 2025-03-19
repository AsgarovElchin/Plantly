package asgarov.elchin.plantly.feature_my_garden.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.plantly.core.navigation.NavigationRoute

@Composable
fun MyGardenScreen(navController: NavController){
    val gardenPlantViewModel: GardenPlantViewModel = hiltViewModel()
    val plantListGarden = gardenPlantViewModel.plantListGardenState.value

    Box(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
        plantListGarden.plantGarden?.let { gardenPlants ->
            MyGardenContent(gardenPlants = gardenPlants,
                onItemClick = { plant ->
                    navController.navigate("plant_detail/${plant.id}")
                },
                onDeleteClick = {plantId->
                    gardenPlantViewModel.deleteGardenPlant(plantId)}, onAddReminderClick = {gardenPlant->
                    navController.navigate(NavigationRoute.SetReminderRoute.createRoute(gardenPlant.id, gardenPlant.commonName))
                         })



            if (plantListGarden.error.isNotBlank()) {
                Text(
                    text = plantListGarden.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center)
                )
            }

            if (plantListGarden.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }}