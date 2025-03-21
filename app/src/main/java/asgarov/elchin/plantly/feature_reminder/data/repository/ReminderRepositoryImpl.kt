package asgarov.elchin.plantly.feature_reminder.data.repository



import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_reminder.data.mapper.toReminder
import asgarov.elchin.plantly.feature_reminder.data.mapper.toReminderDto
import asgarov.elchin.plantly.feature_reminder.data.remote.ReminderApi
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import asgarov.elchin.plantly.feature_reminder.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val reminderApi: ReminderApi
) : ReminderRepository {

    override fun createReminder(reminder: Reminder): Flow<Resource<Reminder>> = flow {
        emit(Resource.Loading())

        try {
            val response = reminderApi.createReminder(reminder.toReminderDto())
            if (response.isSuccessful) {
                response.body()?.let { emit(Resource.Success(it.toReminder())) }
                    ?: emit(Resource.Error("Failed to create reminder"))
            } else {
                emit(Resource.Error("Error creating reminder: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }

    override fun updateReminder(id: Long, reminder: Reminder): Flow<Resource<Reminder>> = flow {
        emit(Resource.Loading())

        try {
            val response = reminderApi.updateReminder(id, reminder.toReminderDto())
            if (response.isSuccessful) {
                response.body()?.let { emit(Resource.Success(it.toReminder())) }
                    ?: emit(Resource.Error("Failed to update reminder"))
            } else {
                emit(Resource.Error("Error updating reminder: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }

    override fun getAllReminders(): Flow<Resource<List<Reminder>>> = flow {
        emit(Resource.Loading())

        try {
            val response = reminderApi.getAllReminders()
            if (response.isSuccessful) {
                response.body()?.let { reminderDtos ->
                    emit(Resource.Success(reminderDtos.map { it.toReminder() }))
                } ?: emit(Resource.Error("No reminders found"))
            } else {
                emit(Resource.Error("Error fetching reminders: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }

    override fun getReminderById(id: Long, reminderType: String): Flow<Resource<Reminder>> = flow {
        emit(Resource.Loading())

        try {
            val response = reminderApi.getReminderById(id,reminderType)
            if (response.isSuccessful) {
                response.body()?.let { emit(Resource.Success(it.toReminder())) }
                    ?: emit(Resource.Error("Reminder not found"))
            } else {
                emit(Resource.Error("Error fetching reminder: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }

    override fun deleteReminder(id: Long): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        try {
            val response = reminderApi.deleteReminder(id)
            if (response.isSuccessful) {
                emit(Resource.Success(Unit))
            } else if (response.code() == 404) {
                emit(Resource.Error("Reminder not found"))
            } else {
                emit(Resource.Error("Error deleting reminder: ${response.message()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        }
    }
}
