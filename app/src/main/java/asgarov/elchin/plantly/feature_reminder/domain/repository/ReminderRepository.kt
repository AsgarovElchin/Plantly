package asgarov.elchin.plantly.feature_reminder.domain.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_reminder.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {

    fun createReminder(reminder: Reminder): Flow<Resource<Reminder>>

    fun updateReminder(id: Long, reminder: Reminder): Flow<Resource<Reminder>>

    fun getAllReminders(): Flow<Resource<List<Reminder>>>

    fun getReminderById(id: Long, reminderType: String): Flow<Resource<Reminder>>

    fun deleteReminder(id: Long): Flow<Resource<Unit>>
}