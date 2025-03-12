package asgarov.elchin.plantly.feature_explore.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun PlantContent(
    plants: LazyPagingItems<Plant>,
    onItemClick: (Plant) -> Unit
) {

    val loggedIds = remember { mutableSetOf<Int>() }

    LaunchedEffect(plants) {
        snapshotFlow { plants.itemSnapshotList }
            .distinctUntilChanged()
            .collect { snapshot ->
                snapshot.filterNotNull().forEach { plant ->
                    if (loggedIds.add(plant.id)) {
                        Log.d("PlantLog", "Plant: $plant")
                    }
                }
            }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(plants.itemCount) { index ->
                val plant = plants[index]
                if (plant != null) {
                    PlantItem(
                        plant = plant,
                        onItemClick = onItemClick
                    )
                }
            }
        }


        if (plants.loadState.append is LoadState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }
    }
}
