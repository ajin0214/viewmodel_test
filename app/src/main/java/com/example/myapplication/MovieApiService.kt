package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getDailyBoxOfficeList(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDate: String
    ): DailyBoxOfficeListResult?
}
