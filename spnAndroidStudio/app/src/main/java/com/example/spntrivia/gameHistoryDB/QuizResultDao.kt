package com.example.spntrivia.gameHistoryDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Insert
    suspend fun insert(quizResult: QuizResult)

    @Query("SELECT * FROM quiz_results ORDER BY id DESC")
    fun getAllResults(): LiveData<List<QuizResult>>
}