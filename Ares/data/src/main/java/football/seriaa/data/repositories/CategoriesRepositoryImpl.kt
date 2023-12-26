package football.seriaa.data.repositories

import football.seriaa.data.dtos.toDomain
import football.seriaa.data.remote.apiservices.CategoriesApiService
import com.example.domain.models.ResultsItem
import football.seriaa.domain.either.Either
import football.seriaa.domain.repositories.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepositoryImpl @Inject constructor(private val service: CategoriesApiService) :
    CategoriesRepository {

    override fun fetchCategories(categories: Int, difficulty: String, amount: Int) = flow<Either<String, List<ResultsItem>>> {
        emit(Either.Right(service.fetchCategories(amount, categories,  difficulty).results.map {
            it.toDomain()
        }))
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Left(it.localizedMessage ?: "ERROR"))
    }
}