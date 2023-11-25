package com.example.spntrivia.ui.trivia.triviaDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TriviaHardDAO {
    @Query("SELECT * FROM hard_questions")
    suspend fun getAllHardQuestions(): List<HardQuestions>
}