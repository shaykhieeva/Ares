package football.seriaa.domain.models

import com.example.domain.models.ResultsItem


data class Response(
    val responseCode: Int = 0,
    val results: List<ResultsItem>,
)