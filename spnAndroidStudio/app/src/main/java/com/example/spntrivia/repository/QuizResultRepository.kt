package com.example.spntrivia.repository

import androidx.lifecycle.LiveData
import com.example.spntrivia.gameHistoryDB.QuizResult
import com.example.spntrivia.gameHistoryDB.QuizResultDao

class QuizResultRepository(private val quizResultDao: QuizResultDao) {
    suspend fun insert(quizResult: QuizResult) {
        quizResultDao.insert(quizResult)
    }
    fun getAllResults(): LiveData<List<QuizResult>> {
        return quizResultDao.getAllResults()
    }
}
