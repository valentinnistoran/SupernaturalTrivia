package com.example.spntrivia.ui.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spntrivia.repository.TriviaRepository
import com.example.spntrivia.triviaDB.EasyQuestions
import kotlinx.coroutines.launch

class TriviaViewModel(private val triviaRepository: TriviaRepository) : ViewModel() {
    private val _currentQuestion = MutableLiveData<EasyQuestions>()
    val currentQuestion: LiveData<EasyQuestions> get() = _currentQuestion

    fun loadRandomQuestion(difficulty: String) {
        viewModelScope.launch {
            val randomQuestion: Any = when (difficulty) {
                "1" -> triviaRepository.getRandomEasyQuestion()
                "2" -> triviaRepository.getRandomMediumQuestion()
                "3" -> triviaRepository.getRandomHardQuestion()
                else -> throw IllegalArgumentException("Invalid difficulty: $difficulty")
            }

            if (randomQuestion is EasyQuestions) {
                _currentQuestion.value = randomQuestion
            } else {
                throw IllegalStateException("Unexpected question type: ${randomQuestion.javaClass.simpleName}")
            }
        }


    }
}
