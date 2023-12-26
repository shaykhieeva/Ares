package com.example.domain.models


data class ResultsItem(
    val difficulty: String = "",
    val question: String = "",
    val correctAnswer: String = "",
    val incorrectAnswers: List<String>?,
    val category: String = "",
    val type: String = ""
)