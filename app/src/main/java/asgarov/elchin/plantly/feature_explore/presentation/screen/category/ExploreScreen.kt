package asgarov.elchin.plantly.feature_explore.presentation.screen.category

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
import asgarov.elchin.plantly.feature_explore.domain.model.Category


@Composable
fun ExploreScreen(navController: NavController) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categoryState = categoryViewModel.categoryState.value

    val plantCategories = listOf(
        "Dracaena",
        "Palm",
        "Anthurium",
        "Other",
        "Aglaonema",
        "Hanging",
        "Bromeliad",
        "Spathiphyllum",
        "Flower",
        "Aralia",
        "Ficus",
        "Sansevieria",
        "Foliage plant",
        "Dieffenbachia",
        "Philodendron",
        "Cactus & Succulent",
        "Schefflera",
        "Topiary",
        "Fern",
        "Grass",
        "Ground Cover"
    )


    Box(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
                CategoryContent(
                    categories = categoryState.categories,
                    onCategoryClick = { selectedCategory ->
                        navController.navigate(NavigationRoute.PlantScreenRoute.createRoute(selectedCategory.categoryName))
                    }
                )

        if (categoryState.error.isNotBlank()) {
            Text(
                text = categoryState.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }

        if (categoryState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
