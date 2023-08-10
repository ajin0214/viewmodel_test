package com.example.myapplication

data class DailyBoxOfficeListResult(
    val boxOfficeResult: BoxOfficeResult?
) {
    fun getDailyBoxOfficeList(): List<BoxOfficeDetailResult>? {
        return boxOfficeResult?.dailyBoxOfficeList
    }
}

