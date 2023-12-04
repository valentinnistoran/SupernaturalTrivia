package com.example.spntrivia.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    val onInstagramButtonClicked = MutableLiveData(false)
    val onTwitterButtonClicked = MutableLiveData(false)

    private val _text = MutableLiveData<String>().apply {
        value = "This is info Fragment"
    }
    val text: LiveData<String> = _text

    fun onClickInstagramButton(){
        onInstagramButtonClicked.value=true
    }

    fun onClickTwitterButton(){
        onTwitterButtonClicked.value=true
    }
}