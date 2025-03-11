package asgarov.elchin.plantly.feature_explore.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import asgarov.elchin.plantly.feature_explore.domain.model.Category


@Composable
fun ExploreScreen() {
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
                    categories = plantCategories.map { Category(it) },
                    onCategoryClick = { selectedCategory ->
                        // Handle category click (e.g., navigate to details)
                    }
                )

//        if (categoryState.error.isNotBlank()) {
//            Text(
//                text = categoryState.error,
//                color = MaterialTheme.colorScheme.error,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(20.dp)
//                    .align(Alignment.Center)
//            )
//        }

        if (categoryState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
