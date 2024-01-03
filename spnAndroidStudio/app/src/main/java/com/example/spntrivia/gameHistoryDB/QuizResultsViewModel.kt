package com.example.spntrivia.gameHistoryDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizResultsViewModel(application: Application):AndroidViewModel(application) {

    private val readAllData: LiveData<List<QuizResult>>
    private val quizResultRepository: QuizResultRepository

    init {
        val gameResultDao = GameDatabase.getDatabase(application).quizResultDao()
        quizResultRepository = QuizResultRepository(gameResultDao)
        readAllData = quizResultRepository.getAllResults()
    }

    fun addQuizResult(quizResult: QuizResult){
        viewModelScope.launch(Dispatchers.IO) {
            quizResultRepository.insertResults(quizResult)
        }
    }

//    fun insertQuizResult(difficulty: Int, score: Int, rank: Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            val quizResult = QuizResult(difficulty = difficulty, score = score, rank = rank)
//            quizResultRepository.insertResults(quizResult)
//        }
//    }

}
