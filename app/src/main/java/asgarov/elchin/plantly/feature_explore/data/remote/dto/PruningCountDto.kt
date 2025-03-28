package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.squareup.moshi.JsonClass

sealed interface PruningCountDto

@JsonClass(generateAdapter = true)
data class PruningCountData(
    val amount: Int,
    val interval: String
) : PruningCountDto

object PruningCountEmpty : PruningCountDto