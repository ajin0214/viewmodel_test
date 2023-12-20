package com.example.myapplication.data.movie

import com.example.myapplication.util.Constants

data class Movie(
    val movieId: String?,
    val rank: String?,
    val movieNm: String?,
    val openDt: String?,
    val audiInten: String?,
    val audiAcc: String?,
    val posterPath: String?,
    val voteAverage: Double?,
    val backdropPath: String?,
    val overview: String?
) {
    fun getPosterUrl(): String? {
        return posterPath?.let { "${Constants.TMDB_POSTER_IMAGE_URL}${it}" }
    }

    fun getBackDropPath(): String? {
        return backdropPath?.let { "${Constants.TMDB_POSTER_IMAGE_URL}${it}" }
    }
}