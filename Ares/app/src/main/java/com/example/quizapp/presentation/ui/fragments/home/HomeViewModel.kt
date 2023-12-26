package com.example.quizapp.presentation.ui.fragments.home

import football.seriaa.domain.usecases.FetchCategoriesUseCase
import com.example.quizapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : BaseViewModel() {
}