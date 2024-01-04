package com.example.spntrivia.ui.profile


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    val onDeleteButtonClicked = MutableLiveData(false)
    fun onClickDeleteButton() {
        onDeleteButtonClicked.value = true
    }
}