package com.example.spntrivia.ui.info

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spntrivia.ui.info.business.FunFact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class InfoViewModel : ViewModel() {

    val onRefreshFunFactButton = MutableLiveData(false)
    val onInstagramButtonClicked = MutableLiveData(false)
    val onTwitterButtonClicked = MutableLiveData(false)

    var funFactsList: List<FunFact>? = null

    fun onClickRefreshFunFactButton() {
        onRefreshFunFactButton.value = true
    }

    fun onClickInstagramButton() {
        onInstagramButtonClicked.value = true
    }

    fun onClickTwitterButton() {
        onTwitterButtonClicked.value = true
    }

    fun loadFunFacts(context: Context) {
        val json: String = context.assets.open("fun_facts.json").bufferedReader().use {
            it.readText()
        }

        val funFactListType = object : TypeToken<List<FunFact>>() {}.type
        funFactsList = Gson().fromJson(json, funFactListType)
    }
}