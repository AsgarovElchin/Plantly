package asgarov.elchin.plantly.feature_my_garden.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_my_garden.domain.repository.GardenPlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GardenPlantViewModel @Inject constructor(
    private val gardenPlantRepository: GardenPlantRepository
):ViewModel() {

    private val _plantListGardenState = mutableStateOf(PlantListGardenState())
    val plantListGardenState: State<PlantListGardenState> = _plantListGardenState

    init {
        getAllGardenPlants()
    }

    private fun getAllGardenPlants(){
        gardenPlantRepository.getAllGardenPlants().onEach {result->
            when(result){
                is Resource.Success->{
                    _plantListGardenState.value = PlantListGardenState(plantGarden = result.data)
                    Log.d("TAG", "getPlantDetailsById: ${result.data}")
                }
                is Resource.Error->{
                    _plantListGardenState.value = PlantListGardenState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _plantListGardenState.value = PlantListGardenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteGardenPlant(id: Long) {
        gardenPlantRepository.deleteGardenPlant(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val updatedList = _plantListGardenState.value.plantGarden?.toMutableList()?.apply {
                        removeAll { it.id == id }
                    } ?: emptyList()

                    _plantListGardenState.value = _plantListGardenState.value.copy(
                        plantGarden = updatedList,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _plantListGardenState.value = _plantListGardenState.value.copy(
                        error = result.message ?: "An error occurred while deleting the plant",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _plantListGardenState.value = _plantListGardenState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}