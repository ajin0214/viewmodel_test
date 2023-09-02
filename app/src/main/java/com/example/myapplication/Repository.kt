package com.example.myapplication

class Repository {
    suspend fun getDailyBoxOfficeList(targetDate: String): DailyBoxOfficeListResult {
        return RetrofitInstance.createKobisApiService().getDailyBoxOfficeList(
            apiKey = "9bd03980caf9c2e3af6dd832245794cf",
            targetDate = targetDate
        )
    }

    suspend fun searchMovie(query: String, year: String): TmdbResponse {
        return RetrofitInstance.createTmdbApiService().searchMovie(
            apiKey = "cad81b4f21905c64ded0cd7048cf7fd4",
            query = query,
            year = year,
            language = "ko"
        )
    }
}
