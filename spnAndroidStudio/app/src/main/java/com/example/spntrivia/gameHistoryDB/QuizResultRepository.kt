package com.example.spntrivia.gameHistoryDB

import androidx.lifecycle.LiveData

class QuizResultRepository(private val quizResultDao: QuizResultDao) {
    suspend fun insertResults(quizResult: QuizResult) {
        quizResultDao.insertResults(quizResult)
    }

    fun getAllResults(): LiveData<List<QuizResult>> {
        return quizResultDao.getAllResults()
    }

    fun getLastResult(): LiveData<QuizResult?> {
        return quizResultDao.getLastResult()
    }

    suspend fun deleteResults(quizResult: List<QuizResult>) = quizResultDao.deleteResults(quizResult)

    suspend fun updateResults(quizResult: QuizResult) = quizResultDao.updateResults(quizResult)
}
