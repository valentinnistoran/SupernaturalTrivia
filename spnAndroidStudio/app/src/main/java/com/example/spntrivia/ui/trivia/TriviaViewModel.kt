package com.example.spntrivia.ui.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spntrivia.repository.TriviaRepository
import com.example.spntrivia.triviaDB.Question
import kotlinx.coroutines.launch

class TriviaViewModel(private val triviaRepository: TriviaRepository) : ViewModel() {
    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> get() = _questions

    fun getQuestionsByDifficulty(difficulty: Int) {
        viewModelScope.launch {
            _questions.value = when (difficulty) {
                1 -> triviaRepository.getAllEasyQuestions()
                2 -> triviaRepository.getAllMediumQuestions()
                3 -> triviaRepository.getAllHardQuestions()
                else -> emptyList()
            }
        }
    }
}
