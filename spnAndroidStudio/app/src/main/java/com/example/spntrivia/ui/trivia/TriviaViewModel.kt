package com.example.spntrivia.ui.trivia


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.triviaDB.QuestionTrivia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class TriviaViewModel : ViewModel() {
    val onQuitButtonClicked = MutableLiveData(false)
    val onSkipButtonClicked = MutableLiveData(false)
    val questionsAnswered = MutableLiveData(1)

    var questions: List<QuestionTrivia> = emptyList()
    var currentQuestionIndex: Int = 0
    val onNextButtonClicked = MutableLiveData(false)

    fun onClickQuitButton() {
        onQuitButtonClicked.value = true
    }

    fun confirmQuit() {
        questionsAnswered.value = 1
    }

    fun onClickSkipButton() {
        onSkipButtonClicked.value = true
        answeredQuestions()
    }

    fun loadQuestions(context: Context, difficultyLevel: Int) {
        questionsAnswered.value = 1
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

            questions = questionsJson.map { question ->
                val imageUrl = question.imageUrl
                val correctAnswer = question.correctAnswer
                val wrongAnswer1 = question.wrongAnswer1
                val wrongAnswer2 = question.wrongAnswer2
                val wrongAnswer3 = question.wrongAnswer3
                question.copy(
                    imageUrl = imageUrl,
                    correctAnswer = correctAnswer,
                    wrongAnswer1 = wrongAnswer1,
                    wrongAnswer2 = wrongAnswer2,
                    wrongAnswer3 = wrongAnswer3
                )
            }.shuffled()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun onClickNextButton() {
        onNextButtonClicked.value = true
        answeredQuestions()
    }

    private fun answeredQuestions() {
        questionsAnswered.value = questionsAnswered.value?.plus(1)
        if (questionsAnswered.value == 11) {
            questionsAnswered.value = 1
        }

    }
}