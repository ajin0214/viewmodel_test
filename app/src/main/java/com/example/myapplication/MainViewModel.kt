package com.example.myapplication


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private var repository: Repository? = null
    private val _searchDailyBoxOfficeList = MutableLiveData<List<BoxOfficeItem>>()
    val searchDailyBoxOfficeList: LiveData<List<BoxOfficeItem>> = _searchDailyBoxOfficeList


    init {
        repository = Repository()
        requestSearchDailyBoxOfficeList()
    }

    private fun requestSearchDailyBoxOfficeList() {
        viewModelScope.launch {
            try {
                val boxOfficeList = repository?.getDailyBoxOfficeList()
                _searchDailyBoxOfficeList.value = boxOfficeList ?: emptyList()
            } catch (e: Exception) {
                // 에러 발생 시 처리
            }
        }
    }
}