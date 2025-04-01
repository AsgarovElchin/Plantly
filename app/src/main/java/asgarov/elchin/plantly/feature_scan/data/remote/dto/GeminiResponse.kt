package asgarov.elchin.plantly.feature_scan.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GeminiResponse(
    val candidates: List<Candidate>
)


@JsonClass(generateAdapter = true)
data class Candidate(
    val content: Content
)


@JsonClass(generateAdapter = true)
data class Content(
    val parts: List<Part>
)


@JsonClass(generateAdapter = true)
data class Part(
    val text: String
)

