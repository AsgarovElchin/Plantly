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
                val body = response.body()
                if (body?.success == true && body.data != null) {
                    emit(Resource.Success(body.data.toReminder()))
                } else {
                    emit(Resource.Error(body?.message ?: "Failed to create reminder"))
                }
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
                val body = response.body()
                if (body?.success == true && body.data != null) {
                    emit(Resource.Success(body.data.toReminder()))
                } else {
                    emit(Resource.Error(body?.message ?: "Failed to update reminder"))
                }
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


            val body = response.body()

            if (response.isSuccessful && body?.data != null) {
                emit(Resource.Success(body.data.map { it.toReminder() }))
            } else {
                emit(Resource.Error(body?.message ?: "Empty response body"))
            }
        } catch (e: HttpException) {
                        emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error("Unexpected error: ${e.localizedMessage}"))
        }
    }

    override fun getReminderById(id: Long, reminderType: String): Flow<Resource<Reminder>> = flow {
        emit(Resource.Loading())

        try {
            val response = reminderApi.getReminderById(id, reminderType)
            val body = response.body()

            if (response.isSuccessful && body?.data != null) {
                emit(Resource.Success(body.data.toReminder()))
            } else {
                emit(Resource.Error(body?.message ?: "Reminder not found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Server error: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error: Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error("Unexpected error: ${e.localizedMessage}"))
        }
    }

    override fun deleteReminder(id: Long): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        try {
            val response = reminderApi.deleteReminder(id)
            val body = response.body()

            if (response.isSuccessful) {
                if (body?.success == true) {
                    emit(Resource.Success(Unit))
                } else {
                    emit(Resource.Error(body?.message ?: "Failed to delete reminder"))
                }
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
