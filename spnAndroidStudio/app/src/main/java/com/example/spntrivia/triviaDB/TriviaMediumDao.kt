package com.example.spntrivia.triviaDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TriviaMediumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMediumQuestions(questions: List<MediumQuestions>)

    @Query("SELECT * FROM medium_questions")
    suspend fun getAllMediumQuestions(): List<MediumQuestions>
}