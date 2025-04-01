package asgarov.elchin.plantly.feature_scan.data.mapper

import asgarov.elchin.plantly.feature_scan.data.remote.dto.GeminiResponse
import asgarov.elchin.plantly.feature_scan.domain.model.PlantScanResult

fun GeminiResponse.toPlantScanResult(): PlantScanResult {
    val resultText = candidates.firstOrNull()
        ?.content?.parts?.firstOrNull()
        ?.text ?: "Unknown plant"

    return PlantScanResult(result = resultText)
}
