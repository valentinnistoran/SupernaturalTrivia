package com.example.spntrivia.gameHistoryDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuizResultDao {
    @Insert
    suspend fun insertResults(quizResult: QuizResult)

    @Update
    suspend fun updateResults(quizResult: QuizResult)

    @Delete
    suspend fun deleteResults(quizResult: QuizResult)

    @Query("SELECT * FROM quiz_results ORDER BY id DESC")
    fun getAllResults(): LiveData<List<QuizResult>>
}