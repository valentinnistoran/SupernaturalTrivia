package com.example.spntrivia.ui.trivia


import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.spntrivia.triviaDB.QuestionTrivia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class TriviaViewModel : ViewModel() {
    var questions: List<QuestionTrivia> = emptyList()
    var currentQuestionIndex: Int = 0

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

            // Shuffle the questions to get a random order
            questions = questionsJson.shuffled()
        } catch (e: IOException) {
            // Handle IOException (e.g., file not found, JSON parsing error)
            e.printStackTrace()
        }
    }
}