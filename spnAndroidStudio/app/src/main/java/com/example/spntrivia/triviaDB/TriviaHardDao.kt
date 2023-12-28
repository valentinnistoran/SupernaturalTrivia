package com.example.spntrivia.triviaDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TriviaHardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHardQuestions(questions: List<HardQuestions>)
    @Query("SELECT * FROM hard_questions")
    suspend fun getAllHardQuestions(): List<HardQuestions>
}