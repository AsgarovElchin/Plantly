package asgarov.elchin.plantly.feature_explore.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_explore.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) : ViewModel() {


    private val _categoryState = mutableStateOf(CategoryState())

    val categoryState: State<CategoryState> = _categoryState

    init {
        getAllCategories()
    }

    private fun getAllCategories(){
        categoryRepository.getAllCategories().onEach {result->
            when(result){
                is Resource.Success->{
                    _categoryState.value = CategoryState(categories = result.data!!)
                }
                is Resource.Error->{
                    _categoryState.value = CategoryState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading->{
                    _categoryState.value = CategoryState(isLoading = true)

                }
            }

        }.launchIn(viewModelScope)
    }



}