package com.example.spntrivia.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    val onHelpButtonClicked = MutableLiveData(false)
    val onAboutUsButtonClicked = MutableLiveData(false)
    val onShareButtonClicked = MutableLiveData(false)

    fun onClickHelpButton() {
        onHelpButtonClicked.value = true
    }

    fun onClickAboutUsButton() {
        onAboutUsButtonClicked.value = true
    }

    fun onClickShareButton() {
        onShareButtonClicked.value = true
    }
}