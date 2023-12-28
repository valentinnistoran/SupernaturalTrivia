package com.example.spntrivia.triviaDB

import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class QuizDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        GlobalScope.launch(Dispatchers.IO) {
            val questions = loadQuestionsFromJson(context, "easy_questions.json")
            val triviaDao =
                Room.databaseBuilder(context, TriviaDatabase::class.java, "trivia_database")
                    .build().easyQuestionsDao()
            triviaDao.insertAllEasyQuestions(questions)
        }
    }

    private fun loadQuestionsFromJson(context: Context, fileName: String): List<EasyQuestions> {
        val json: String? = try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }

        return Gson().fromJson(json, object : TypeToken<List<EasyQuestions>>() {}.type)
            ?: emptyList()
    }
}