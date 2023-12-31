package com.example.spntrivia.ui.endQuiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EndQuizViewModel : ViewModel() {

    val onBackHomeButtonClicked = MutableLiveData(false)
    val onOpenProfileButtonClicked = MutableLiveData(false)


    val finalScore = MutableLiveData(0)

    fun onClickBackHomeButton() {
        onBackHomeButtonClicked.value = true
    }

    fun onClickOpenProfileButton() {
        onOpenProfileButtonClicked.value = true
    }

    fun calculateFinalScore() {

    }

}