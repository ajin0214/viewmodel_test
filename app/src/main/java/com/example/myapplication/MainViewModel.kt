package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isHelloWorldVisible = MutableLiveData(true)
    val isHelloWorldVisible: LiveData<Boolean> = _isHelloWorldVisible


    fun onClickTvClickButton() {
        _isHelloWorldVisible.value = _isHelloWorldVisible.value != true
    }
}