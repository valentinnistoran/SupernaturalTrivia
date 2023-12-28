package com.example.spntrivia.triviaDB

import androidx.room.Entity
import androidx.room.PrimaryKey

interface Question {
    val id: Long
    val questionText: String
    val imageUrl: String
    val correctAnswer: String
    val wrongAnswer1: String
    val wrongAnswer2: String
    val wrongAnswer3: String
}

@Entity(tableName = "easy_questions")
data class EasyQuestions(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    override val questionText: String,
    override val imageUrl: String,
    override val correctAnswer: String,
    override val wrongAnswer1: String,
    override val wrongAnswer2: String,
    override val wrongAnswer3: String
) : Question

@Entity(tableName = "medium_questions")
data class MediumQuestions(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    override val questionText: String,
    override val imageUrl: String,
    override val correctAnswer: String,
    override val wrongAnswer1: String,
    override val wrongAnswer2: String,
    override val wrongAnswer3: String
) : Question

@Entity(tableName = "hard_questions")
data class HardQuestions(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    override val questionText: String,
    override val imageUrl: String,
    override val correctAnswer: String,
    override val wrongAnswer1: String,
    override val wrongAnswer2: String,
    override val wrongAnswer3: String
) : Question
