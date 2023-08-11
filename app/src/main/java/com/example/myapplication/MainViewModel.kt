package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel : ViewModel() {
    private val repository: Repository by lazy { Repository() }

    private val _dailyBoxOfficeList = MutableLiveData<List<DailyBoxOfficeResult>>()
    val dailyBoxOfficeList: LiveData<List<DailyBoxOfficeResult>> = _dailyBoxOfficeList

    private var calendar: Calendar = Calendar.getInstance()
    private var yesterday = calendar.add(Calendar.DAY_OF_YEAR, -1)
    //private var yesterday변수선언 안해주면 왜오류나는지절대모름
    private var targetDate: String = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(calendar.time)

    init {
        requestSearchDailyBoxOfficeList()
    }

    private fun requestSearchDailyBoxOfficeList() {
        viewModelScope.launch {
            try {
                repository.getDailyBoxOfficeList(targetDate)?.let {
                    _dailyBoxOfficeList.value = it.getDailyBoxOfficeList() ?: emptyList()
                }
            } catch (e: Exception) {
                // todo-만갑: 에러 처리
            }
        }
    }
}