package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel : ViewModel() {
    private val repository: Repository by lazy { Repository() }

    private val _dailyBoxOfficeList = MutableLiveData<List<DailyBoxOfficeResult>>()
    val dailyBoxOfficeList: LiveData<List<DailyBoxOfficeResult>> = _dailyBoxOfficeList

    init {
        requestSearchDailyBoxOfficeList()
    }

    private fun requestSearchDailyBoxOfficeList() {
        val targetDate = getTargetDate()

        viewModelScope.launch {
            try {
                val dailyBoxOfficeListResult = repository.getDailyBoxOfficeList(targetDate = targetDate)
                _dailyBoxOfficeList.value = dailyBoxOfficeListResult.getDailyBoxOfficeList() ?: emptyList()

            } catch (e: Exception) {
                // todo-만갑: 에러 처리
            }
        }
    }

    private fun getTargetDate(): String {
        val oneDayInMillis: Long = 24 * 60 * 60 * 1000
        val yesterdayInMillis: Long = System.currentTimeMillis() - oneDayInMillis
        return SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date(yesterdayInMillis))
    }
}