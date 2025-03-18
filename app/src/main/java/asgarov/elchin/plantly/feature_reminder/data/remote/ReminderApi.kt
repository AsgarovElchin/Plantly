package asgarov.elchin.plantly.feature_reminder.data.remote

import asgarov.elchin.plantly.feature_reminder.data.remote.dto.ReminderDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReminderApi {

    @POST("reminders")
    suspend fun createReminder(@Body reminderDto: ReminderDto): Response<ReminderDto>

    @PUT("reminders/{id}")
    suspend fun updateReminder(@Path("id") id: Long, @Body reminderDto: ReminderDto): Response<ReminderDto>

    @GET("reminders")
    suspend fun getAllReminders(): Response<List<ReminderDto>>

    @GET("reminders/{id}")
    suspend fun getReminderById(@Path("id") id: Long): Response<ReminderDto>

    @DELETE("reminders/{id}")
    suspend fun deleteReminder(@Path("id") id: Long): Response<Unit>

}