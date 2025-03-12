package asgarov.elchin.plantly.feature_explore.data.remote


import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface PlantsDataApi {

    @GET("v2/species-list")
    suspend fun getAllPlants(
        @Query("key") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("q") query: String? = null,
        @Query("order") order: String? = null,
        @Query("edible") edible: Boolean? = null,
        @Query("poisonous") poisonous: Boolean? = null,
        @Query("cycle") cycle: String? = null,
        @Query("watering") watering: String? = null,
        @Query("sunlight") sunlight: String? = null,
        @Query("indoor") indoor: Boolean? = null
    ): PlantListDto

}