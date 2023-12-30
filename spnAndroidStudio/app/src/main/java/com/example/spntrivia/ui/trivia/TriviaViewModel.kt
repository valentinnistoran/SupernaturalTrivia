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
    val onAnswer1ButtonClicked = MutableLiveData(false)
    val onAnswer2ButtonClicked = MutableLiveData(false)
    val onAnswer3ButtonClicked = MutableLiveData(false)
    val onAnswer4ButtonClicked = MutableLiveData(false)
    val onNextButtonClicked = MutableLiveData(false)


    val questionsAnswered = MutableLiveData(1)

    var questions: List<QuestionTrivia> = emptyList()
    var currentQuestionIndex: Int = 0

    var answer1: String = ""
    var answer2: String = ""
    var answer3: String = ""
    var answer4: String = ""

    val triviaQuestionLiveData = MutableLiveData<QuestionTrivia>()


    //onClick functions
    fun onClickQuitButton() {
        onQuitButtonClicked.value = true
    }

    fun onClickSkipButton() {
        onSkipButtonClicked.value = true
        answeredQuestions()
        loadNextQuestion()
    }

    fun onClickAnswer1Button() {
        onAnswer1ButtonClicked.value = true
    }

    fun onClickAnswer2Button() {
        onAnswer2ButtonClicked.value = true
    }

    fun onClickAnswer3Button() {
        onAnswer3ButtonClicked.value = true
    }

    fun onClickAnswer4Button() {
        onAnswer4ButtonClicked.value = true
    }

    fun onClickNextButton() {
        onNextButtonClicked.value = true
        answeredQuestions()
        loadNextQuestion()
    }

    //questions loading functions
    fun loadQuestions(context: Context, difficultyLevel: Int) {
        questionsAnswered.value = 1
        currentQuestionIndex = 0
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
            loadNextQuestion()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun setAnswerButtons(question: QuestionTrivia) {
        val answers = listOf(
            question.correctAnswer,
            question.wrongAnswer1,
            question.wrongAnswer2,
            question.wrongAnswer3
        ).shuffled()

        answer1 = answers[0]
        answer2 = answers[1]
        answer3 = answers[2]
        answer4 = answers[3]
    }

    fun loadNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex <= questions.size) {
            loadQuestionAtIndex(currentQuestionIndex)
        } else {
            //go to end page
        }
    }

    private fun loadQuestionAtIndex(index: Int) {
        if (index in questions.indices) {
            val question = questions[index]
            setAnswerButtons(question)
            triviaQuestionLiveData.value = question
        }
    }

    //keep track of answered questions
    private fun answeredQuestions() {
        questionsAnswered.value = questionsAnswered.value?.plus(1)
        if (questionsAnswered.value == 11) {
            questionsAnswered.value = 0
            //go to end page
        }
    }

    //check if the button pressed is the right answer
    fun isRightAnswer() {

    }

    //confirm quitting the quiz
    fun confirmQuit() {
        questionsAnswered.value = 0
    }


}