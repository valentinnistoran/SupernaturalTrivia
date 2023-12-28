package com.example.spntrivia.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    val onHelpButtonClicked = MutableLiveData(false)
    val onAboutUsButtonClicked = MutableLiveData(false)
    val onShareButtonClicked = MutableLiveData(false)

    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
    val text: LiveData<String> = _text

    fun onClickHelpButton(){
        onHelpButtonClicked.value=true
    }

    fun onClickAboutUsButton(){
        onAboutUsButtonClicked.value=true
    }

    fun onClickShareButton(){
        onShareButtonClicked.value=true
    }
}