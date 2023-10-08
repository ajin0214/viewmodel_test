package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.data.movie.MovieRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.launch

class MainViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _dailyBoxOfficeList = MutableLiveData<List<Movie>>()
    val dailyBoxOfficeList: LiveData<List<Movie>> = _dailyBoxOfficeList

    init {
        getDailyBoxOfficeList()
    }

    private fun getDailyBoxOfficeList() {
        viewModelScope.launch {
            try {
                val targetDate = getTargetDate()
                _dailyBoxOfficeList.value = movieRepository.getDailyBoxOfficeList(targetDate = targetDate)
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