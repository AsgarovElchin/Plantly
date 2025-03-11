package asgarov.elchin.plantly.feature_explore.data.remote


import asgarov.elchin.plantly.feature_explore.data.remote.dto.CategoryDto
import retrofit2.http.GET
import retrofit2.http.Header


interface PlantsDataApi {

    @GET("7643/all+categories/no")
    suspend fun getAllCategories(
        @Header("Authorization") apiKey: String
    ): List<CategoryDto>

}