package asgarov.elchin.plantly.feature_explore.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import asgarov.elchin.plantly.R
import asgarov.elchin.plantly.core.navigation.NavigationRoute


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
        "Cycle" to listOf("Perennial", "Annual", "Biennial", "Biannual"),
        "Watering" to listOf("Frequent", "Average", "Minimum", "None"),
        "Sunlight" to listOf("Full Shade", "Part Shade", "Sun Part Shade", "Full Sun"),
        "Indoor" to listOf("Yes", "No")
    )

    val currentFilterValues = mapOf(
        "Order" to filter.order.orEmpty(),
        "Edible" to filter.edible?.toString().orEmpty(),
        "Poisonous" to filter.poisonous?.toString().orEmpty(),
        "Cycle" to filter.cycle.orEmpty(),
        "Watering" to filter.watering.orEmpty(),
        "Sunlight" to filter.sunlight.orEmpty(),
        "Indoor" to filter.indoor?.toString().orEmpty()
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = { showAdvancedFilters = true }
            ) {
                Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filter")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FilterBar(
                searchQuery = filter.query.orEmpty(),
                labelText = "Plant name",
                onSearchQueryChange = { newName ->
                    plantViewModel.updateFilter { copy(query = newName) }
                }
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when {
                    plants.loadState.refresh is LoadState.Loading -> {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }

                    plants.loadState.refresh is LoadState.Error -> {
                        val error = (plants.loadState.refresh as LoadState.Error).error
                        Text(
                            text = "Error: ${error.localizedMessage}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    plants.loadState.refresh is LoadState.NotLoading && plants.itemCount == 0 -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize().padding(24.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dracaena),
                                contentDescription = "No Results",
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "No plants found.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    else -> {
                        PlantContent(plants = plants) { plant ->
                            navController.navigate(
                                NavigationRoute.PlantDetailRoute.createRoute(plant.id)
                            )
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = showAdvancedFilters,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        FilterDialog(
            title = "Advanced Filters",
            filterOptions = filterOptions,
            currentFilterValues = currentFilterValues,
            onDismiss = { showAdvancedFilters = false },
            onApply = { newFilter ->
                plantViewModel.updateFilter {
                    copy(
                        order = newFilter["Order"],
                        edible = newFilter["Edible"]?.toBooleanStrictOrNull(),
                        poisonous = newFilter["Poisonous"]?.toBooleanStrictOrNull(),
                        cycle = newFilter["Cycle"],
                        watering = newFilter["Watering"],
                        sunlight = newFilter["Sunlight"],
                        indoor = newFilter["Indoor"]?.toBooleanStrictOrNull()
                    )
                }
                showAdvancedFilters = false
            }
        )
    }
}
