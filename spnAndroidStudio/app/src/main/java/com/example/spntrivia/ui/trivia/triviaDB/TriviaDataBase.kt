package com.example.spntrivia.ui.trivia.triviaDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EasyQuestions::class, MediumQuestions::class, HardQuestions::class],
    version = 1,
    exportSchema = false
)
abstract class TriviaDataBase : RoomDatabase() {
    abstract fun easyQuestionsDAO(): TriviaEasyDAO
    abstract fun mediumQuestionDao(): TriviaMediumDAO
    abstract fun hardQuestionDao(): TriviaHardDAO
}