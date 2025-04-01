package asgarov.elchin.plantly.feature_scan.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import asgarov.elchin.plantly.feature_scan.domain.repository.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import kotlinx.coroutines.flow.launchIn
import android.net.Uri
import android.util.Base64


@HiltViewModel
class ScanViewModel @Inject constructor(
    private val repository: GeminiRepository
) : ViewModel() {

    private val _state = mutableStateOf(ScanState())
    val state: State<ScanState> = _state

    fun identifyPlant(base64Image: String) {
        println("🔁 identifyPlant triggered...")
        repository.identifyPlant(base64Image).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ScanState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ScanState(result = result.data?.result ?: "")
                }
                is Resource.Error -> {
                    _state.value = ScanState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }


    fun encodeImage(context: Context, uri: Uri): String {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bytes = inputStream?.readBytes() ?: return ""
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }
}
