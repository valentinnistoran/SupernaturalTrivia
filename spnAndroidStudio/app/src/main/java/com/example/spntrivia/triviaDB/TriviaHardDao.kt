package com.example.spntrivia.triviaDB

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TriviaHardDao {
    @Query("SELECT * FROM hard_questions")
    suspend fun getAllHardQuestions(): List<HardQuestions>
}