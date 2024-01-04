package com.example.spntrivia.ui.endQuiz

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.gameHistoryDB.QuizResult
import com.example.spntrivia.ui.endQuiz.business.Ranking
import com.example.spntrivia.ui.trivia.business.QuestionTrivia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class EndQuizViewModel : ViewModel() {

    val onBackHomeButtonClicked = MutableLiveData(false)

    val rankLiveData = MutableLiveData<Ranking?>()

    private val lastQuizResult = MutableLiveData<QuizResult?>()
    private var ranksList: List<Ranking>? = null
    var rank: Ranking? = null
    private var rankingList: List<Ranking> = emptyList()
    fun onClickBackHomeButton() {
        onBackHomeButtonClicked.value = true
    }

    fun loadRanks(context: Context) {
        rankLiveData.value = null
        try {
            val json: String = context.assets.open("ranking.json").bufferedReader().use {
                it.readText()
            }
            val rankingListType = object : TypeToken<List<Ranking>>() {}.type
            ranksList = Gson().fromJson(json, rankingListType)

            rankingList = ranksList?.map { rank ->
                val imageUrl = rank.imageUrl
                val rankScore = rank.rankScore
                val rankTitle = rank.rankTitle
                val rankMessage = rank.rankMessage
                rank.copy(
                    imageUrl = imageUrl,
                    rankScore = rankScore,
                    rankTitle = rankTitle,
                    rankMessage = rankMessage
                )
            } ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun observeLastQuizResult(quizResult: QuizResult?) {
        lastQuizResult.value = quizResult
        findCurrentRank(quizResult)
    }

    private fun findCurrentRank(quizResult: QuizResult?) {
        for (rank in rankingList) {
            if (quizResult != null) {
                if (rank.rankScore == quizResult.rank) {
                    rankLiveData.value = rank
                }
            }
        }
    }

}