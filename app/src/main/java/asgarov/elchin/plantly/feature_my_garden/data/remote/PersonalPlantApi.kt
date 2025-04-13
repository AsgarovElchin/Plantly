package asgarov.elchin.plantly.feature_my_garden.data.remote

import asgarov.elchin.plantly.authentication.data.remote.dto.ApiResponseDto
import asgarov.elchin.plantly.feature_my_garden.data.remote.dto.GardenPlantDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PersonalPlantApi {

    @POST("plants")
    suspend fun addGardenPlant(@Body gardenPlantDto: GardenPlantDto): Response<ApiResponseDto<GardenPlantDto>>

    @GET("plants")
    suspend fun getAllGardenPlants(): Response<ApiResponseDto<List<GardenPlantDto>>>

    @DELETE("plants/{id}")
    suspend fun deleteGardenPlant(@Path("id") id: Long): Response<ApiResponseDto<Unit>>

}