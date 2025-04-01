package asgarov.elchin.plantly.feature_scan.data.remote

import asgarov.elchin.plantly.feature_scan.data.remote.dto.GeminiRequest
import asgarov.elchin.plantly.feature_scan.data.remote.dto.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApi {
    @POST("models/gemini-2.0-flash:generateContent")
    suspend fun generatePlantInfo(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}
