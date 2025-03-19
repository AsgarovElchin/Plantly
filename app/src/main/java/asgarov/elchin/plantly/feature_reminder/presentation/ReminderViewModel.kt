package asgarov.elchin.plantly.feature_reminder.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import asgarov.elchin.plantly.feature_reminder.domain.model.ReminderType
import asgarov.elchin.plantly.feature_reminder.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val reminderRepository: ReminderRepository
) : ViewModel() {

    private val _reminderState = mutableStateOf(ReminderState())
    val reminderState: State<ReminderState> = _reminderState

    init {
        val plantId: Long = savedStateHandle.get<String>("plantId")?.toLongOrNull() ?: 0L
        val plantName: String = savedStateHandle.get<String>("plantName") ?: ""
        _reminderState.value = ReminderState(
            reminder = Reminder(
                id = 0,
                plantId = plantId,
                plantName = plantName,
                reminderType = ReminderType.MISTING,
                repeatEvery = 1,
                repeatUnit = "Days",
                reminderTime = LocalDateTime.now()
            )
        )
    }

    fun updateState(reminder: Reminder) {
        _reminderState.value = _reminderState.value.copy(reminder = reminder)
    }

    fun createReminder(reminder: Reminder) {
        reminderRepository.createReminder(reminder).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _reminderState.value = ReminderState(reminder = result.data)
                }
                is Resource.Error -> {
                    _reminderState.value = ReminderState(error = result.message ?: "Error creating reminder")
                }
                is Resource.Loading -> {
                    _reminderState.value = ReminderState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getReminderById(id: Long) {
        reminderRepository.getReminderById(id).onEach { result ->
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
        reminderRepository.updateReminder(id, reminder).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _reminderState.value = ReminderState(reminder = result.data)
                }
                is Resource.Error -> {
                    _reminderState.value = ReminderState(error = result.message ?: "Error updating reminder")
                }
                is Resource.Loading -> {
                    _reminderState.value = ReminderState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}