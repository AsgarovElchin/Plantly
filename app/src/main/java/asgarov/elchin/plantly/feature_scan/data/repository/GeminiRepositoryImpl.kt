package asgarov.elchin.plantly.feature_scan.data.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_scan.data.remote.GeminiApi
import asgarov.elchin.plantly.feature_scan.data.remote.dto.GeminiContent
import asgarov.elchin.plantly.feature_scan.data.remote.dto.GeminiPart
import asgarov.elchin.plantly.feature_scan.data.remote.dto.GeminiRequest
import asgarov.elchin.plantly.feature_scan.data.remote.dto.InlineData
import asgarov.elchin.plantly.feature_scan.domain.model.PlantScanResult
import asgarov.elchin.plantly.feature_scan.domain.repository.GeminiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named


class GeminiRepositoryImpl @Inject constructor(
    private val api: GeminiApi,
    @Named("GeminiApiKey") private val apiKey: String
) : GeminiRepository {

    override fun identifyPlant(base64Image: String): Flow<Resource<PlantScanResult>> = flow {
        emit(Resource.Loading())

        try {
            val prompt = """
    You are a helpful plant assistant. The user has uploaded an image. First, determine if the image contains a plant.

    If it is NOT a plant, respond only with:
    This is not a plant. Please take a photo of a plant.

    If it is a plant, respond with the following sections in this exact order:

    Common Name:  
    Scientific Name:  
    Watering Instructions:  
    Sunlight Needs:  
    Pruning Advice:

    Please keep the tone friendly and concise. Use short and informative sentences. Do not include any emojis.
""".trimIndent()

            val request = GeminiRequest(
                contents = listOf(
                    GeminiContent(
                        parts = listOf(
                            GeminiPart(text = prompt),
                            GeminiPart(inlineData = InlineData(data = base64Image))
                        )
                    )
                )
            )

            val response = api.generatePlantInfo(apiKey, request)

            val text = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
            if (text != null) {
                emit(Resource.Success(PlantScanResult(text)))
            } else {
                emit(Resource.Error("No response from Gemini."))
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred."))
        }
    }
}