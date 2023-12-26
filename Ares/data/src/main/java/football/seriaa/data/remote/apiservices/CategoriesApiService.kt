package football.seriaa.data.remote.apiservices

import football.seriaa.data.dtos.ResponseDto
import football.seriaa.data.dtos.ResultsItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesApiService {

    @GET("api.php")
    suspend fun fetchCategories(
        @Query("amount") amount: Int?,
        @Query("category") category: Int?,
        @Query("difficulty") difficulty: String
    ): ResponseDto<ResultsItemDto>
}