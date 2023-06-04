package com.example.myapplication

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainViewModel : ViewModel() {

    init {
        requestSearchDailyBoxOfficeList()
    }

    private val _isHelloWorldVisible = MutableLiveData(true)
    val isHelloWorldVisible: LiveData<Boolean> = _isHelloWorldVisible

    private val _searchDailyBoxOfficeList = MutableLiveData<String>()
    val searchDailyBoxOfficeList: LiveData<String> = _searchDailyBoxOfficeList


    fun onClickTvClickButton() {
        _isHelloWorldVisible.value = _isHelloWorldVisible.value != true
    }

    private fun requestSearchDailyBoxOfficeList() {
        val request = Request.Builder()
            .url("https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=9bd03980caf9c2e3af6dd832245794cf&targetDt=20230501")
            .build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()

                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    responseBody?.let {
                        _searchDailyBoxOfficeList.value = it
                    }
                }
            }
        })
    }
}