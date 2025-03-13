package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.HardinessDto
import asgarov.elchin.plantly.feature_explore.domain.model.Hardiness

fun HardinessDto.toHardiness(): Hardiness {
    return Hardiness(
        min = min,
        max = max
    )
}