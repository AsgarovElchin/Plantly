package asgarov.elchin.plantly.feature_explore.presentation.screen.plant_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantCareRepository
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    private val plantDetailRepository: PlantDetailRepository,
    private val plantCareRepository: PlantCareRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _plantDetailState = mutableStateOf(PlantDetailState())

    val plantDetailState: State<PlantDetailState> = _plantDetailState

    private val _plantCareState = mutableStateOf(PlantCareState())

    val plantCareState: State<PlantCareState> = _plantCareState

    init {
        val plantId = savedStateHandle.get<String>("plantId")?.toIntOrNull() ?: 0
        getPlantDetailsById(plantId)
        getPlantCareGuidesById(plantId)
    }


    private fun getPlantDetailsById(id:Int){
        plantDetailRepository.getPlantDetailsById(id).onEach {result->
            when(result){
                is Resource.Success->{
                    _plantDetailState.value = PlantDetailState(plantDetail = result.data)
                    Log.d("TAG", "getPlantDetailsById: ${result.data}")
                }
                is Resource.Error->{
                    _plantDetailState.value = PlantDetailState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _plantDetailState.value = PlantDetailState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getPlantCareGuidesById(speciesId:Int){
        plantCareRepository.getPlantCareGuidesById(speciesId).onEach {result->
            when(result){
                is Resource.Success->{
                    _plantCareState.value = PlantCareState(plantCare = result.data)
                    Log.d("TAG", "getPlantCareGuidesById: ${result.data}")
                }
                is Resource.Error->{
                    _plantCareState.value = PlantCareState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _plantCareState.value = PlantCareState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }


}