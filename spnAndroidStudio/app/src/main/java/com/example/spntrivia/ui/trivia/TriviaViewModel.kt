package com.example.spntrivia.ui.trivia


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.triviaDB.QuestionTrivia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class TriviaViewModel : ViewModel() {
    var questions: List<QuestionTrivia> = emptyList()
    var currentQuestionIndex: Int = 0
    val onNextButtonClicked = MutableLiveData(false)
    val onSkipButtonClicked = MutableLiveData(false)
    val onQuitButtonClicked = MutableLiveData(false)

    fun loadQuestions(context: Context, difficultyLevel: Int) {
        val fileName = when (difficultyLevel) {
            1 -> "easy_questions.json"
            2 -> "medium_questions.json"
            3 -> "hard_questions.json"
            else -> throw IllegalArgumentException("Invalid difficulty level")
        }

        try {
            val json: String = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }

            val questionListType = object : TypeToken<List<QuestionTrivia>>() {}.type
            val questionsJson = Gson().fromJson<List<QuestionTrivia>>(json, questionListType)

            questions = questionsJson.shuffled()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun onClickNextButton() {
        onNextButtonClicked.value = true
    }

    fun onClickSkipButton() {
        onSkipButtonClicked.value = true
    }

    fun onClickQuitButton() {
        onQuitButtonClicked.value = true
    }
}