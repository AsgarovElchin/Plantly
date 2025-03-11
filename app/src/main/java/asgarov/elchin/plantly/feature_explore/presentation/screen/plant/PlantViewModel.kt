package asgarov.elchin.plantly.feature_explore.presentation.screen.plant

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.domain.repository.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantRepository: PlantRepository,
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private val _plantState = mutableStateOf(PlantState())

    val plantState: State<PlantState> = _plantState

    init {
        savedStateHandle.get<String>("categoryName")?.let { category->
            getAllPlantsByCategory(category)
        }
    }



    private fun getAllPlantsByCategory(category:String){
        plantRepository.getAllPlantsByCategory(category).onEach {result->
            when(result){
                is Resource.Success->{
                    _plantState.value = PlantState(plants = result.data!!)

                }
                is Resource.Error->{
                    _plantState.value = PlantState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _plantState.value = PlantState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }



}