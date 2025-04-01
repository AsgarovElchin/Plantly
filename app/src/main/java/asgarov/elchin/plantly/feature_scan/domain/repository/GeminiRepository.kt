package asgarov.elchin.plantly.feature_scan.domain.repository

import asgarov.elchin.plantly.core.utils.Resource
import asgarov.elchin.plantly.feature_scan.domain.model.PlantScanResult
import kotlinx.coroutines.flow.Flow

interface GeminiRepository {
    fun identifyPlant(base64Image: String): Flow<Resource<PlantScanResult>>
}
