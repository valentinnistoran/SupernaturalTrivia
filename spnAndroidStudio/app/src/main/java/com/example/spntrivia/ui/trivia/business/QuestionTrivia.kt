package com.example.spntrivia.ui.trivia.business

data class QuestionTrivia(
    val questionText: String,
    val imageUrl: String,
    val correctAnswer: String,
    val wrongAnswer1: String,
    val wrongAnswer2: String,
    val wrongAnswer3: String
)