package com.example.myapplication.data.movie.source.remote.tmdb

data class TmdbResponse(
    val page: Int?,
    val results: List<TmdbMovie>?,
    val total_pages: Int?,
    val total_results: Int?
)

data class TmdbMovie(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)