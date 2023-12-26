package com.example.quizapp.presentation.ui.fragments.question

import androidx.lifecycle.viewModelScope
import football.seriaa.domain.either.Either
import football.seriaa.domain.usecases.FetchCategoriesUseCase
import com.example.quizapp.presentation.base.BaseViewModel
import com.example.quizapp.presentation.models.ResultsItemUI
import com.example.quizapp.presentation.models.toUI
import com.example.quizapp.presentation.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : BaseViewModel() {

    private val _categoryState =
        MutableStateFlow<UIState<List<ResultsItemUI>>>(UIState.Loading())
    val categoryState = _categoryState.asStateFlow()

    fun fetchCategory(category: Int, difficulty: String, amount: Int) {
        viewModelScope.launch {
            fetchCategoriesUseCase(category, difficulty, amount).collect { either ->
                when (either) {
                    is Either.Left ->
                        either.message.let {
                            _categoryState.value = UIState.Error(either.message.toString())
                        }
                    is Either.Right ->
                        either.data?.let { data ->
                            _categoryState.value = UIState.Success(data.map { item ->
                                item.toUI()
                            })
                        }
                }
            }
        }
    }
}