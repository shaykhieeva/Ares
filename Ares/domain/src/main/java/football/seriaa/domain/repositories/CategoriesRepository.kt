package football.seriaa.domain.repositories

import football.seriaa.domain.either.Either
import com.example.domain.models.ResultsItem
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun fetchCategories(categories: Int, difficulty: String, amount: Int): Flow<Either<String, List<ResultsItem>>>
}