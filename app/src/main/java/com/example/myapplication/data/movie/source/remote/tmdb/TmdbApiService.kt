package com.example.myapplication.data.movie.source.remote.tmdb

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("year") year: String,
        @Query("language") language: String
    ): TmdbResponse
}