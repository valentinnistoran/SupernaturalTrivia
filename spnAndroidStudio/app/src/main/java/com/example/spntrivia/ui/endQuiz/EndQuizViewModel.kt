package com.example.spntrivia.ui.endQuiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.gameHistoryDB.QuizResult

class EndQuizViewModel : ViewModel() {

    val onBackHomeButtonClicked = MutableLiveData(false)

    val calculatedScore = MutableLiveData(0)
    val chosenDifficulty = MutableLiveData(0)
    val calculatedRank = MutableLiveData(0)

    val lastQuizResult = MutableLiveData<QuizResult?>()
    fun observeLastQuizResult(quizResult: QuizResult?) {
        lastQuizResult.value = quizResult
        quizResult?.let {
            calculatedScore.value = it.score
            chosenDifficulty.value = it.difficulty
            calculatedRank.value = it.rank
        }
    }

    fun onClickBackHomeButton() {
        onBackHomeButtonClicked.value = true
    }

}