package com.example.spntrivia.ui.trivia


import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.ui.trivia.business.QuestionTrivia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class TriviaViewModel() : ViewModel() {

    val levelDifficulty = MutableLiveData(0)

    val onQuitButtonClicked = MutableLiveData(false)
    val onSkipButtonClicked = MutableLiveData(false)
    val onAnswer1ButtonClicked = MutableLiveData(false)
    val onAnswer2ButtonClicked = MutableLiveData(false)
    val onAnswer3ButtonClicked = MutableLiveData(false)
    val onAnswer4ButtonClicked = MutableLiveData(false)
    val onNextButtonClicked = MutableLiveData(false)

    var questions: List<QuestionTrivia> = emptyList()
    var currentQuestionIndex: Int = 0

    val answer1 = MutableLiveData<String>()
    val answer2 = MutableLiveData<String>()
    val answer3 = MutableLiveData<String>()
    val answer4 = MutableLiveData<String>()

    var isAnswer1Clicked = false
    var isAnswer2Clicked = false
    var isAnswer3Clicked = false
    var isAnswer4Clicked = false

    val isAnswerButtonClicked = MutableLiveData<Boolean>(false)

    val isAnswer1ButtonClicked = MutableLiveData<Boolean>(false)
    val isAnswer2ButtonClicked = MutableLiveData<Boolean>(false)
    val isAnswer3ButtonClicked = MutableLiveData<Boolean>(false)
    val isAnswer4ButtonClicked = MutableLiveData<Boolean>(false)
    var answerColor = MutableLiveData<Boolean>(false)

    val triviaQuestionLiveData = MutableLiveData<QuestionTrivia>()

    val questionsAnswered = MutableLiveData(1)
    val score = MutableLiveData<Int>(0)
    val finalScore = MutableLiveData<Long?>(0)
    private var correctAnswer: String = ""

    val timerDuration: Long = 60000
    val remainingTime = MutableLiveData<Long>()
    val triviaTimerLiveData = MutableLiveData<Long>()
    val timerFinished = MutableLiveData(false)

    val timer: CountDownTimer = object : CountDownTimer(timerDuration, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            triviaTimerLiveData.value = millisUntilFinished / 1000
        }

        override fun onFinish() {
            remainingTime.value = triviaTimerLiveData.value!!
            timerFinished.value = true
        }
    }

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
        isAnswerButtonClicked.value = true
        if (isAnswer2ButtonClicked.value == false && isAnswer3ButtonClicked.value == false && isAnswer4ButtonClicked.value == false) {
            isAnswer1ButtonClicked.value = true
        }
    }

    fun onClickAnswer2Button() {
        onAnswer2ButtonClicked.value = true
        isAnswerButtonClicked.value = true
        if (isAnswer1ButtonClicked.value == false && isAnswer3ButtonClicked.value == false && isAnswer4ButtonClicked.value == false) {
            isAnswer2ButtonClicked.value = true
        }
    }

    fun onClickAnswer3Button() {
        onAnswer3ButtonClicked.value = true
        isAnswerButtonClicked.value = true
        if (isAnswer1ButtonClicked.value == false && isAnswer2ButtonClicked.value == false && isAnswer4ButtonClicked.value == false) {
            isAnswer3ButtonClicked.value = true
        }
    }

    fun onClickAnswer4Button() {
        onAnswer4ButtonClicked.value = true
        isAnswerButtonClicked.value = true
        if (isAnswer1ButtonClicked.value == false && isAnswer2ButtonClicked.value == false && isAnswer3ButtonClicked.value == false) {
            isAnswer4ButtonClicked.value = true
        }
    }

    fun onClickNextButton() {
        onNextButtonClicked.value = true
        answeredQuestions()
        loadNextQuestion()
        resetAnswerButtonStates()
    }

    //questions loading functions
    fun loadQuestions(context: Context, difficultyLevel: Int) {
        questionsAnswered.value = 1
        currentQuestionIndex = 0
        levelDifficulty.value = difficultyLevel
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

    private fun setAnswerButtons(currentQuestion: QuestionTrivia) {
        correctAnswer = currentQuestion.correctAnswer
        val answers = listOf(
            currentQuestion.correctAnswer,
            currentQuestion.wrongAnswer1,
            currentQuestion.wrongAnswer2,
            currentQuestion.wrongAnswer3
        ).shuffled()

        answer1.value = answers[0]
        answer2.value = answers[1]
        answer3.value = answers[2]
        answer4.value = answers[3]
    }

    fun loadNextQuestion() {
        answerColor.value = false
        currentQuestionIndex++
        if (currentQuestionIndex <= questions.size) {
            loadQuestionAtIndex(currentQuestionIndex)
        }
    }

    private fun loadQuestionAtIndex(index: Int) {
        isAnswerButtonClicked.value = false
        isAnswer1ButtonClicked.value = false
        isAnswer2ButtonClicked.value = false
        isAnswer3ButtonClicked.value = false
        isAnswer4ButtonClicked.value = false
        if (index in questions.indices) {
            val question = questions[index]
            setAnswerButtons(question)
            triviaQuestionLiveData.value = question
        }
    }

    //keep track of answered questions
    private fun answeredQuestions() {
        questionsAnswered.value = questionsAnswered.value?.plus(1)
        if (questionsAnswered.value == 10) {
            questionsAnswered.value = 0
            calculateScore()
            //go to end page
        }
    }

    //check if the button pressed is the right answer
    fun isRightAnswer(selectedAnswer: String) {
        if (!isAnswer1Clicked && !isAnswer2Clicked && !isAnswer3Clicked && !isAnswer4Clicked) {
            if (selectedAnswer == correctAnswer) {
                score.value = (score.value ?: 0) + 1
                answerColor.value = true
            }
            when (selectedAnswer) {
                answer1.value -> isAnswer1Clicked = true
                answer2.value -> isAnswer2Clicked = true
                answer3.value -> isAnswer3Clicked = true
                answer4.value -> isAnswer4Clicked = true
            }
        }
    }

    private fun resetAnswerButtonStates() {
        isAnswer1Clicked = false
        isAnswer2Clicked = false
        isAnswer3Clicked = false
        isAnswer4Clicked = false
    }

    //confirm quitting the quiz
    fun confirmQuit() {
        questionsAnswered.value = 0
    }
    private fun calculateScore() {
        finalScore.value = score.value?.toLong()?.times(100)
    }

}