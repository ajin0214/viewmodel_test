package com.example.myapplication

data class DailyBoxOfficeResult(
    val boxOfficeResult: BoxOfficeResult?
) {
    fun getDailyBoxOfficeResults(): List<BoxOfficeDetailResult>? {
        return boxOfficeResult?.dailyBoxOfficeList
    }
}
