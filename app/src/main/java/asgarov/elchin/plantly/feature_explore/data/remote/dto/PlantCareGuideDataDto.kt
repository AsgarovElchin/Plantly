package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlantCareGuideDataDto(
    @SerializedName("common_name") val commonName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("scientific_name") val scientificName: List<String>,
    @SerializedName("section") val sections: List<CareSectionDto>,
    @SerializedName("species_id") val speciesId: Int
)