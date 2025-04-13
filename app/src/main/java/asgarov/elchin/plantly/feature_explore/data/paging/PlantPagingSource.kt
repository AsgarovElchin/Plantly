package asgarov.elchin.plantly.feature_explore.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import asgarov.elchin.plantly.feature_explore.data.mapper.toPlant
import asgarov.elchin.plantly.feature_explore.data.remote.PlantsDataApi
import asgarov.elchin.plantly.feature_explore.domain.model.Plant
import asgarov.elchin.plantly.feature_explore.domain.use_case.PlantFilter
import retrofit2.HttpException
import java.io.IOException

class PlantPagingSource(
    private val api: PlantsDataApi,
    private val apiKey: String,
    private val filter: PlantFilter
) : PagingSource<Int, Plant>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Plant> {
        val page = params.key ?: 1

        return try {
            val response = api.getAllPlants(
                apiKey = apiKey,
                page = page,
                query = filter.query,
                order = filter.order,
                edible = filter.edible,
                poisonous = filter.poisonous,
                cycle = filter.cycle,
                watering = filter.watering,
                sunlight = filter.sunlight,
                indoor = filter.indoor
            )

            val plants = response.data?.map { it.toPlant() }.orEmpty()
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (page < (response.lastPage ?: page)) page + 1 else null

            LoadResult.Page(
                data = plants,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            val errorMessage = when (e.code()) {
                400 -> "Bad request. Please try again."
                404 -> "No plants found."
                500 -> "Server is unavailable. Try again later."
                else -> "Unexpected API error."
            }
            LoadResult.Error(Exception(errorMessage))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Plant>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPage = state.closestPageToPosition(anchorPosition)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }
}