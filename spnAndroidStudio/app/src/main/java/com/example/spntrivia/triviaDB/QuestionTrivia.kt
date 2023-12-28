package com.example.spntrivia.triviaDB

data class QuestionTrivia(
    val questionText: String,
    val imageUrl: String,
    val correctAnswer: String,
    val wrongAnswer1: String,
    val wrongAnswer2: String,
    val wrongAnswer3: String
)