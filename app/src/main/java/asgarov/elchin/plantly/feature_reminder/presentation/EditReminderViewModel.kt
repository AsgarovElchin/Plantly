package asgarov.elchin.plantly.feature_reminder.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import asgarov.elchin.plantly.feature_reminder.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditReminderViewModel @Inject constructor(
    private val reminderRepository: ReminderRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _reminderState = mutableStateOf(ReminderState())
    val reminderState: State<ReminderState> = _reminderState

    init {
        val reminderId = savedStateHandle.get<String>("reminderId")?.toLongOrNull()
        val reminderType = savedStateHandle.get<String>("reminderType")

        if (reminderId != null && !reminderType.isNullOrBlank()) {
            getReminderById(reminderId, reminderType)
        }


    }

    private fun getReminderById(id: Long, reminderType: String) {
        reminderRepository.getReminderById(id, reminderType)
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _reminderState.value = ReminderState(reminder = result.data)
                    }
                    is Resource.Error -> {
                        _reminderState.value = ReminderState(error = result.message ?: "Error fetching reminder")
                    }
                    is Resource.Loading -> {
                        _reminderState.value = ReminderState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun updateReminder(id: Long, reminder: Reminder) {
        reminderRepository.updateReminder(id, reminder)
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _reminderState.value = ReminderState(reminder = result.data)
                    }
                    is Resource.Error -> {
                        _reminderState.value = ReminderState(error = result.message ?: "Error fetching reminder")
                    }
                    is Resource.Loading -> {
                        _reminderState.value = ReminderState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }
}
