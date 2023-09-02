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
                val dailyBoxOfficeList = dailyBoxOfficeListResult.getDailyBoxOfficeList() ?: emptyList()

                val dailyBoxOfficeListWithPoster = dailyBoxOfficeList.map {
                    val year = it.openDt.substring(0,4)
                    val tmdbResponse = repository.searchMovie(it.movieNm, year)
                    val posterPath = tmdbResponse.results?.firstOrNull()?.poster_path
                    it.copy(posterPath = posterPath)
                }
                _dailyBoxOfficeList.value = dailyBoxOfficeListWithPoster

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