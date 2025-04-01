package asgarov.elchin.plantly.feature_scan.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GeminiRequest(
    val contents: List<GeminiContent>
)


@JsonClass(generateAdapter = true)
data class GeminiContent(
    val parts: List<GeminiPart>
)


@JsonClass(generateAdapter = true)
data class GeminiPart(
    val text: String? = null,
    val inlineData: InlineData? = null
)


@JsonClass(generateAdapter = true)
data class InlineData(
    val mimeType: String = "image/jpeg",
    val data: String
)
