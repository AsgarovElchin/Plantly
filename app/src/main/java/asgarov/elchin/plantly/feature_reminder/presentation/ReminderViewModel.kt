package asgarov.elchin.plantly.feature_reminder.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_reminder.domain.model.PreviousData
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
    savedStateHandle: SavedStateHandle,
    private val reminderRepository: ReminderRepository
) : ViewModel() {

    private val _reminderState = mutableStateOf(ReminderState())
    val reminderState: State<ReminderState> = _reminderState


    private val _successMessage = mutableStateOf("")
    val successMessage: State<String> = _successMessage

    private val _reminderListState = mutableStateOf(ReminderListState())
    val reminderListState: State<ReminderListState> = _reminderListState




    init {
        val plantId: Long = savedStateHandle.get<Long>("plantId") ?: 0L
        val plantName: String = savedStateHandle.get<String>("plantName") ?: ""


        _reminderState.value = ReminderState(
            reminder = Reminder(
                id = 0,
                plantId = plantId,
                plantName = plantName,
                reminderType = ReminderType.MISTING,
                repeatEvery = 1,
                repeatUnit = "Days",
                reminderTime = LocalDateTime.now(),
                previousData = PreviousData.TODAY,
                nextReminderDateTime = LocalDateTime.now()
            )
        )

        getAllReminders()
    }


    fun createReminder(reminder: Reminder) {
        reminderRepository.createReminder(reminder).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _reminderState.value = ReminderState(reminder = result.data)
                    _successMessage.value = "Reminder successfully added"
                    getAllReminders()
                }
                is Resource.Error -> {
                    _reminderState.value = _reminderState.value.copy(
                        error = result.message ?: "It is already added",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _reminderState.value = _reminderState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }




    fun getAllReminders() {
        reminderRepository.getAllReminders().onEach { result ->
            when (result) {
                is Resource.Success -> {
                                       _reminderListState.value = ReminderListState(reminders = result.data)
                }

                is Resource.Error -> {
                    _reminderListState.value = ReminderListState(error = result.message ?: "Error updating reminder")
                }

                is Resource.Loading -> {
                    _reminderListState.value = ReminderListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



    fun deleteReminderByPlant(id: Long) {
        reminderRepository.deleteReminder(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val updatedList = _reminderListState.value.reminders?.toMutableList()?.apply {
                        removeAll { it.id == id }
                    } ?: emptyList()

                    _reminderListState.value = _reminderListState.value.copy(
                        reminders = updatedList,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _reminderListState.value = _reminderListState.value.copy(
                        error = result.message ?: "An error occurred while deleting the plant",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _reminderListState.value = _reminderListState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}