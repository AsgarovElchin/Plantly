package asgarov.elchin.plantly.feature_explore.data.mapper

import asgarov.elchin.plantly.feature_explore.data.remote.dto.DefaultImageDto
import asgarov.elchin.plantly.feature_explore.domain.model.DefaultImage

fun DefaultImageDto.toDefaultImage(): DefaultImage {
    return DefaultImage(
        originalUrl = originalUrl?: "",
        regularUrl = regularUrl?: "",
        mediumUrl = mediumUrl?: "",
        smallUrl = smallUrl?: "",
        thumbnailUrl = thumbnailUrl?: ""
    )
}