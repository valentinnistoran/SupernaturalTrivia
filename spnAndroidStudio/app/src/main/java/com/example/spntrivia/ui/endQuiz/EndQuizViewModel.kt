package com.example.spntrivia.ui.endQuiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.gameHistoryDB.QuizResult

class EndQuizViewModel : ViewModel() {

    val onBackHomeButtonClicked = MutableLiveData(false)
    val onOpenProfileButtonClicked = MutableLiveData(false)

    val calculatedScore = MutableLiveData(0)
    val chosenDifficulty = MutableLiveData(0)
    val calculatedRank = MutableLiveData(0)

    val lastQuizResult = MutableLiveData<QuizResult?>()

    // Observe the last quiz result and update the properties accordingly
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

    fun onClickOpenProfileButton() {
        onOpenProfileButtonClicked.value = true
    }

}