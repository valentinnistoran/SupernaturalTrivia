package com.example.spntrivia.ui.trivia.triviaDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TriviaMediumDAO {
    @Query("SELECT * FROM medium_questions")
    suspend fun getAllMediumQuestions(): List<MediumQuestions>
}