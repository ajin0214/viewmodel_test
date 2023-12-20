package com.example.myapplication.data.movie.source.remote

import com.example.myapplication.data.movie.source.remote.kobis.DailyBoxOfficeListResult
import com.example.myapplication.data.movie.source.remote.kobis.KobisApiService
import com.example.myapplication.data.movie.source.remote.tmdb.TmdbApiService
import com.example.myapplication.data.movie.source.remote.tmdb.TmdbResponse

class MovieRemoteDatasource(
    private val kobisApiService: KobisApiService,
    private val tmdbApiService: TmdbApiService
) {
    suspend fun searchDailyBoxOfficeList(targetDate: String): DailyBoxOfficeListResult {
        return kobisApiService.searchDailyBoxOfficeList(
            apiKey = "9bd03980caf9c2e3af6dd832245794cf",
            targetDate = targetDate
        )
    }

    suspend fun searchMovie(query: String, year: String): TmdbResponse {
        return tmdbApiService.searchMovie(
            apiKey = "cad81b4f21905c64ded0cd7048cf7fd4",
            query = query,
            year = year,
            language = "ko"
        )
    }
}