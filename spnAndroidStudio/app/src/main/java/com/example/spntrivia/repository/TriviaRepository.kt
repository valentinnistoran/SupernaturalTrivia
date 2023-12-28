package com.example.spntrivia.repository

import com.example.spntrivia.triviaDB.EasyQuestions
import com.example.spntrivia.triviaDB.HardQuestions
import com.example.spntrivia.triviaDB.MediumQuestions
import com.example.spntrivia.triviaDB.TriviaDatabase

class TriviaRepository(private val triviaDatabase: TriviaDatabase) {

    suspend fun getAllEasyQuestions(): List<EasyQuestions> {
        return triviaDatabase.easyQuestionsDao().getAllEasyQuestions()
    }

    suspend fun getAllMediumQuestions(): List<MediumQuestions> {
        return triviaDatabase.mediumQuestionDao().getAllMediumQuestions()
    }

    suspend fun getAllHardQuestions(): List<HardQuestions> {
        return triviaDatabase.hardQuestionDao().getAllHardQuestions()
    }

    suspend fun getRandomEasyQuestion(): EasyQuestions {
        val allQuestions = triviaDatabase.easyQuestionsDao().getAllEasyQuestions()
        return allQuestions.random()
    }

    suspend fun getRandomMediumQuestion(): MediumQuestions {
        val allQuestions = triviaDatabase.mediumQuestionDao().getAllMediumQuestions()
        return allQuestions.random()
    }

    suspend fun getRandomHardQuestion(): HardQuestions {
        val allQuestions = triviaDatabase.hardQuestionDao().getAllHardQuestions()
        return allQuestions.random()
    }

    suspend fun insertEasyQuestions(questions: List<EasyQuestions>) {
        triviaDatabase.easyQuestionsDao().insertAllEasyQuestions(questions)
    }

    suspend fun insertMediumQuestions(questions: List<MediumQuestions>) {
        triviaDatabase.mediumQuestionDao().insertAllMediumQuestions(questions)
    }

    suspend fun insertHardQuestions(questions: List<HardQuestions>) {
        triviaDatabase.hardQuestionDao().insertAllHardQuestions(questions)
    }


}
