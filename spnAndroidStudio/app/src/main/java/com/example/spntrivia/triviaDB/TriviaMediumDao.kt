package com.example.spntrivia.triviaDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TriviaMediumDao {
    @Query("SELECT * FROM medium_questions")
    suspend fun getAllMediumQuestions(): List<MediumQuestions>
}