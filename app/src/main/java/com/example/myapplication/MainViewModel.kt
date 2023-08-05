package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository: Repository by lazy { Repository() }

    private val _dailyBoxOfficeList = MutableLiveData<List<DailyBoxOfficeResult>>()
    val dailyBoxOfficeList: LiveData<List<DailyBoxOfficeResult>> = _dailyBoxOfficeList

    init {
        requestSearchDailyBoxOfficeList()
    }

    private fun requestSearchDailyBoxOfficeList() {
        viewModelScope.launch {
            try {
                repository.getDailyBoxOfficeList()?.let {
                    _dailyBoxOfficeList.value = it.getDailyBoxOfficeList() ?: emptyList()
                }
            } catch (e: Exception) {
                // todo-만갑: 에러 처리
            }
        }
    }
}