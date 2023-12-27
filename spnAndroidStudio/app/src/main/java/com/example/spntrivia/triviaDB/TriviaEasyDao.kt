package com.example.spntrivia.triviaDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TriviaEasyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<EasyQuestions>)
    @Query("SELECT * FROM easy_questions")
    suspend fun getAllEasyQuestions(): List<EasyQuestions>
}