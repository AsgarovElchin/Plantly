package asgarov.elchin.plantly.feature_explore.presentation.screen.plant

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


@Composable
fun PlantScreen(){

    val plantViewModel: PlantViewModel = hiltViewModel()
    val plantState = plantViewModel.plantState.value

    Box(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
        PlantContent(
            plants = plantState.plants,
            onPlantClick = { selectedPlant ->
              //  navController.navigate(NavigationRoute.ExploreRoute(selectedCategory.categoryName).route)
            }
        )

        if (plantState.error.isNotBlank()) {
            Text(
                text = plantState.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (plantState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }




}