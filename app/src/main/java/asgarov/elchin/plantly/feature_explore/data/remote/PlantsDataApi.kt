package asgarov.elchin.plantly.feature_explore.data.remote


import asgarov.elchin.plantly.feature_explore.data.remote.dto.CategoryDto
import asgarov.elchin.plantly.feature_explore.data.remote.dto.PlantDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface PlantsDataApi {

    @GET("7643/all+categories")
    suspend fun getAllCategories(
        @Header("Authorization") apiKey: String
    ): List<CategoryDto>


    @GET("7644/plant+information+by+category")
    suspend fun getAllPlantsByCategory(
        @Query("category") category: String,
        @Header("Authorization") apiKey: String
    ):List<PlantDto>

}