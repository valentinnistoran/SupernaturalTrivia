package com.example.spntrivia.ui.trivia.triviaDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TriviaEasyDAO {
    @Query("SELECT * FROM easy_questions")
    suspend fun getAllEasyQuestions(): List<EasyQuestions>
}