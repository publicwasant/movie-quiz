package com.example.moviequiz.models

data class QuizModel (
    val question: String,
    val image: Int,
    val choices: ArrayList<String>,
    val choiceCorrect: Int,
)