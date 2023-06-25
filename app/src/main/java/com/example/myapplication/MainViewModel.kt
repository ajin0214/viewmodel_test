package com.example.myapplication


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private var repository: Repository? = null
    private val _searchDailyBoxOfficeList = MutableLiveData<List<BoxOfficeDetailResult>>()
    val searchDailyBoxOfficeList: LiveData<List<BoxOfficeDetailResult>> = _searchDailyBoxOfficeList


    init {
        repository = Repository()
        requestSearchDailyBoxOfficeList()
    }

    private fun requestSearchDailyBoxOfficeList() {
        viewModelScope.launch {
            try {
                repository?.getDailyBoxOffice()?.let {
                    _searchDailyBoxOfficeList.value = it.getDailyBoxOfficeResults() ?: emptyList()
                }

            } catch (e: Exception) {
                // 에러 발생 시 처리
            }
        }
    }
}