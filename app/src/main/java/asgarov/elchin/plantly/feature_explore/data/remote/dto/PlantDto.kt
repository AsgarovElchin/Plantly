package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlantDto(
    @SerializedName("authority") val authority: String?,
    @SerializedName("common_name") val commonName: String,
    @SerializedName("cultivar") val cultivar: String?,
    @SerializedName("default_image") val defaultImage: DefaultImageDto?,
    @SerializedName("family") val family: String?,
    @SerializedName("genus") val genus: String?,
    @SerializedName("hybrid") val hybrid: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("other_name") val otherNames: List<String>,
    @SerializedName("scientific_name") val scientificNames: List<String>,
    @SerializedName("species_epithet") val speciesEpithet: String?,
    @SerializedName("subspecies") val subspecies: String?,
    @SerializedName("variety") val variety: String?
)