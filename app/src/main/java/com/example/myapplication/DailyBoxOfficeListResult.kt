package com.example.myapplication

data class DailyBoxOfficeListResult(
    val boxOfficeResult: BoxOfficeResult?
) {
    fun getDailyBoxOfficeList(): List<DailyBoxOfficeResult>? {
        return boxOfficeResult?.dailyBoxOfficeList
    }
}
