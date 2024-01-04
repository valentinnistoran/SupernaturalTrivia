package com.example.spntrivia.ui.endQuiz

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.gameHistoryDB.QuizResult
import com.example.spntrivia.ui.endQuiz.business.Ranking
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class EndQuizViewModel : ViewModel() {

    val onBackHomeButtonClicked = MutableLiveData(false)

    val calculatedScore = MutableLiveData<Long?>(0)
    val chosenDifficulty = MutableLiveData(0)
    val calculatedRank = MutableLiveData(0)

    val lastQuizResult = MutableLiveData<QuizResult?>()
    var ranksList: List<Ranking>? = null
    var rank: Ranking? = null

    fun observeLastQuizResult(quizResult: QuizResult?) {
        lastQuizResult.value = quizResult
        quizResult?.let {
            calculatedScore.value = it.score
            chosenDifficulty.value = it.difficulty
            calculatedRank.value = it.rank
        }
    }

    fun onClickBackHomeButton() {
        onBackHomeButtonClicked.value = true
    }

    fun loadRanks(context: Context) {
        try {
            val json: String = context.assets.open("ranking.json").bufferedReader().use {
                it.readText()
            }
            val rankingListType = object : TypeToken<List<Ranking>>() {}.type
            ranksList = Gson().fromJson(json, rankingListType)
        } catch (e: IOException) {
            e.printStackTrace()
        }


    }

}