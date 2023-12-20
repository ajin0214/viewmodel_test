package com.example.myapplication.data.movie.source.remote.kobis

import retrofit2.http.GET
import retrofit2.http.Query

interface KobisApiService {
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    suspend fun searchDailyBoxOfficeList(
        @Query("key") apiKey: String,
        @Query("targetDt") targetDate: String
    ): DailyBoxOfficeListResult
}
