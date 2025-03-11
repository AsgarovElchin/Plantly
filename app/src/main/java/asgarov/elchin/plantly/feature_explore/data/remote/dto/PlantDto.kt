package asgarov.elchin.plantly.feature_explore.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlantDto(
    @SerializedName("Categories") val category: String?,
    @SerializedName("Common name (fr.)") val commonNameFr: String?,
    @SerializedName("Img") val imageUrl: String?,
    @SerializedName("Zone") val zone: List<String?>,
    @SerializedName("Family") val family: String?,
    @SerializedName("Common name") val commonNames: List<String?>,
    @SerializedName("Latin name") val latinName: String?,
    @SerializedName("Other names") val otherNames: String?,
    @SerializedName("Description") val description: String?,
    @SerializedName("Origin") val origin: List<String?>,
    @SerializedName("id") val id: String?,
    @SerializedName("Climat") val climat: String?
    )
