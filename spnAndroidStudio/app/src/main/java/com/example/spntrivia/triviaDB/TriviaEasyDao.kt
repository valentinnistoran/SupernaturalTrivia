package com.example.spntrivia.triviaDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TriviaEasyDao {
    @Query("SELECT * FROM easy_questions")
    suspend fun getAllEasyQuestions(): List<EasyQuestions>
}