package com.example.spntrivia.triviaDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EasyQuestions::class, MediumQuestions::class, HardQuestions::class],
    version = 1,
    exportSchema = false
)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun easyQuestionsDao(): TriviaEasyDao
    abstract fun mediumQuestionDao(): TriviaMediumDao
    abstract fun hardQuestionDao(): TriviaHardDao

    companion object {
        @Volatile
        private var Instance: TriviaDatabase? = null

        fun getDatabase(context: Context): TriviaDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TriviaDatabase::class.java, "trivia_database")
                    .build().also { Instance = it }
            }
        }
    }
}