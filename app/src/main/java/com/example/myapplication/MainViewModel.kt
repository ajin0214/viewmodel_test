package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale
import android.util.Log

class MainViewModel : ViewModel() {
    private var repository: Repository? = null
    private val _searchDailyBoxOfficeList = MutableLiveData<List<BoxOfficeDetailResult>>()
    val searchDailyBoxOfficeList: LiveData<List<BoxOfficeDetailResult>> = _searchDailyBoxOfficeList

    private var calendar: Calendar = Calendar.getInstance()
    private var yesterday = calendar.add(Calendar.DAY_OF_YEAR, -1)
    //private var yesterday변수선언 안해주면 왜오류나는지절대모름
    private var targetDate: String = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(calendar.time)



    init {
        repository = Repository()
        requestSearchDailyBoxOfficeList()
    }

    private fun requestSearchDailyBoxOfficeList() {
        viewModelScope.launch {
            try {Log.d("날짜",targetDate)
                repository?.getDailyBoxOffice(targetDate)?.let {
                    _searchDailyBoxOfficeList.value = it.getDailyBoxOfficeResults() ?: emptyList()
                }
            }
            catch (e: Exception) {
            }
        }
    }
}