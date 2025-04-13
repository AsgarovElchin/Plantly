package asgarov.elchin.plantly.feature_reminder.data.remote

import asgarov.elchin.plantly.authentication.data.remote.dto.ApiResponseDto
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
    suspend fun createReminder(
        @Body reminderDto: ReminderDto
    ): Response<ApiResponseDto<ReminderDto>> // ✅ Wrapped

    @PUT("reminders/{id}")
    suspend fun updateReminder(
        @Path("id") id: Long,
        @Body reminderDto: ReminderDto
    ): Response<ApiResponseDto<ReminderDto>> // ✅ Wrapped

    @GET("reminders")
    suspend fun getAllReminders(): Response<ApiResponseDto<List<ReminderDto>>> // ✅ Already correct

    @GET("reminders/{id}/{reminderType}")
    suspend fun getReminderById(
        @Path("id") id: Long,
        @Path("reminderType") reminderType: String
    ): Response<ApiResponseDto<ReminderDto>> // ✅ Updated from ReminderDto to ApiResponseDto

    @DELETE("reminders/{id}")
    suspend fun deleteReminder(
        @Path("id") id: Long
    ): Response<ApiResponseDto<Unit>> // ✅ Optional: wrap Unit too for consistency

}
