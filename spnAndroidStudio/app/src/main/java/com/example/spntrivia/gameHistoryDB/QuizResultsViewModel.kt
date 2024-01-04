package com.example.spntrivia.gameHistoryDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizResultsViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<QuizResult>>
    val lastResult: LiveData<QuizResult?>
    private val quizResultRepository: QuizResultRepository

    init {
        val gameResultDao = GameDatabase.getDatabase(application).quizResultDao()
        quizResultRepository = QuizResultRepository(gameResultDao)
        readAllData = quizResultRepository.getAllResults()
        lastResult = quizResultRepository.getLastResult()
    }

    fun addQuizResult(quizResult: QuizResult) {
        viewModelScope.launch(Dispatchers.IO) {
            quizResultRepository.insertResults(quizResult)
        }
    }

    fun deleteQuizResult(quizResult: List<QuizResult>){
        viewModelScope.launch(Dispatchers.IO) {
            quizResultRepository.deleteResults(quizResult)
        }
    }


}
