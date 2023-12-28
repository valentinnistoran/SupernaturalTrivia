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
            insertQuestions(context, "easy_questions.json") { questions ->
                insertEasyQuestions(questions)
            }

            insertQuestions(context, "medium_questions.json") { questions ->
                insertMediumQuestions(questions)
            }

            insertQuestions(context, "hard_questions.json") { questions ->
                insertHardQuestions(questions)
            }
        }
    }

    private suspend fun insertQuestions(
        context: Context,
        fileName: String,
        insertFunction: suspend (List<Question>) -> Unit
    ) {
        val json: String? = try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }

        val questions = Gson().fromJson<List<Question>>(json, object : TypeToken<List<Question>>() {}.type)
            ?: emptyList()

        insertFunction(questions)
    }

    private suspend fun insertEasyQuestions(questions: List<Question>) {
        val triviaDao = Room.databaseBuilder(
            context,
            TriviaDatabase::class.java,
            "trivia_database"
        ).build().easyQuestionsDao()
        triviaDao.insertAllEasyQuestions(questions as List<EasyQuestions>)
    }

    private suspend fun insertMediumQuestions(questions: List<Question>) {
        val triviaDao = Room.databaseBuilder(
            context,
            TriviaDatabase::class.java,
            "trivia_database"
        ).build().mediumQuestionDao()
        triviaDao.insertAllMediumQuestions(questions as List<MediumQuestions>)
    }

    private suspend fun insertHardQuestions(questions: List<Question>) {
        val triviaDao = Room.databaseBuilder(
            context,
            TriviaDatabase::class.java,
            "trivia_database"
        ).build().hardQuestionDao()
        triviaDao.insertAllHardQuestions(questions as List<HardQuestions>)
    }
}
