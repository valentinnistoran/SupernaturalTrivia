package com.example.spntrivia.ui.trivia.triviaDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "easy_questions")
data class EasyQuestions(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val questionText: String,
    val imageUrl: String,
    val correctAnswer: String,
    val wrongAnswer1: String,
    val wrongAnswer2: String,
    val wrongAnswer3: String
)

@Entity(tableName = "medium_questions")
data class MediumQuestions(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val questionText: String,
    val imageUrl: String,
    val correctAnswer: String,
    val wrongAnswer1: String,
    val wrongAnswer2: String,
    val wrongAnswer3: String
)

@Entity(tableName = "hard_questions")
data class HardQuestions(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val questionText: String,
    val imageUrl: String,
    val correctAnswer: String,
    val wrongAnswer1: String,
    val wrongAnswer2: String,
    val wrongAnswer3: String
)