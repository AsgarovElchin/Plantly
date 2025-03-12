package asgarov.elchin.plantly.feature_explore.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun ExploreScreen(navController: NavController) {
    val plantViewModel: PlantViewModel = hiltViewModel()
    val filter by plantViewModel.filterState.collectAsState()
    val plants = plantViewModel.plantsFlow.collectAsLazyPagingItems()

    var showAdvancedFilters by remember { mutableStateOf(false) }

    val filterOptions = mapOf(
        "Order" to listOf("Asc", "Desc"),
        "Edible" to listOf("Yes", "No"),
        "Poisonous" to listOf("Yes", "No"),
        "Cycle" to listOf("Perennial", "Annual", "Biennial","Biannual"),
        "Watering" to listOf("Frequent", "Average", "Minimum","None"),
        "Sunlight" to listOf("Full Shade", "Part Shade", "Sun Part Shade", "Full Sun"),
        "Indoor" to listOf("Yes", "No")
    )

    val currentFilterValues = mapOf(
        "Order" to filter.order.orEmpty(),
        "Edible" to (filter.edible.toString() ?: ""),
        "Poisonous" to (filter.poisonous.toString() ?:""),
        "Cycle" to filter.cycle.orEmpty(),
        "Watering" to filter.watering.orEmpty(),
        "Sunlight" to filter.sunlight.orEmpty(),
        "Indoor" to (filter.indoor.toString() ?: "")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        FilterBar(
            searchQuery = filter.query.orEmpty(),
            labelText = "Plant name",
            onSearchQueryChange = { newName ->
                plantViewModel.updateFilter(filter.copy(query = newName))
            },
            onFilterClick = { showAdvancedFilters = true }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (plants.loadState.refresh) {
                is androidx.paging.LoadState.Loading -> {
                    CircularProgressIndicator()
                }
                is androidx.paging.LoadState.Error -> {
                    val error =
                        (plants.loadState.refresh as androidx.paging.LoadState.Error).error
                    Text(
                        text = "Error: ${error.localizedMessage}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    PlantContent(plants = plants, onItemClick = {plant->
                      //  navController.navigate(AppRoute.CharacterDetailRoute(character.id))
                    })
                }
            }
        }
    }


    if (showAdvancedFilters) {
        FilterDialog(
            title = "Advanced Filters",
            filterOptions = filterOptions,
            currentFilterValues = currentFilterValues,
            onDismiss = { showAdvancedFilters = false },
            onApply = { newFilter ->
                plantViewModel.updateFilter(
                    filter.copy(
                        order = newFilter["Order"],
                        edible = newFilter["Edible"]?.toBooleanStrictOrNull(),
                        poisonous = newFilter["Poisonous"]?.toBooleanStrictOrNull(),
                        cycle = newFilter["Cycle"],
                        watering = newFilter["Watering"],
                        sunlight = newFilter["Sunlight"],
                        indoor = newFilter["Indoor"]?.toBooleanStrictOrNull()
                    )
                )
                showAdvancedFilters = false
            }
        )
    }
}