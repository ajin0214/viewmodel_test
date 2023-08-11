package com.example.myapplication

class Repository {
    suspend fun getDailyBoxOfficeList(targetDate: String): DailyBoxOfficeListResult? {
        return RetrofitInstance.createMovieApiService().getDailyBoxOfficeList(
            apiKey = "9bd03980caf9c2e3af6dd832245794cf",
            targetDate = targetDate
        )
    }
}
