package com.example.spntrivia.ui.trivia.triviaDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia_questions")
data class TriviaQuestionsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val imagePath: String?
)