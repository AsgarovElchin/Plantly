package asgarov.elchin.plantly.feature_explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import asgarov.elchin.plantly.feature_explore.domain.use_case.GetAllPlantsUseCase
import asgarov.elchin.plantly.feature_explore.domain.use_case.PlantFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
   private val getAllPlantsUseCase: GetAllPlantsUseCase
):ViewModel(){

    private val _filterState = MutableStateFlow(PlantFilter())
    val filterState = _filterState.asStateFlow()


    val plantsFlow = _filterState.flatMapLatest {
            filter->
        getAllPlantsUseCase(filter)
    }.cachedIn(viewModelScope)

    fun updateFilter(newFilter: PlantFilter) {
        viewModelScope.launch {
            _filterState.emit(newFilter)
        }

    }}