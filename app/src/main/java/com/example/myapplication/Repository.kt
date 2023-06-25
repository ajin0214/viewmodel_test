package com.example.myapplication

class Repository {
    suspend fun getDailyBoxOfficeList(): List<BoxOfficeItem>? {
        val boxOfficeItem = RetrofitInstance.createMovieApiService().getDailyBoxOfficeList(
            apiKey = "9bd03980caf9c2e3af6dd832245794cf",
            targetDate = "20230501"
        )
        return boxOfficeItem?.dailyBoxOfficeList
    }
}
