package com.example.myapplication.data.movie

import com.example.myapplication.data.movie.source.remote.MovieRemoteDatasource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class MovieRepository(
    private val movieRemoteDatasource: MovieRemoteDatasource
) {
    private var movieList: List<Movie>? = null

    suspend fun getDailyBoxOfficeList(targetDate: String): List<Movie>? {
        return coroutineScope {
            val dailyBoxOfficeListResult = movieRemoteDatasource.searchDailyBoxOfficeList(targetDate = targetDate)
            val dailyBoxOfficeList = dailyBoxOfficeListResult.getDailyBoxOfficeList()

            val deferredList = dailyBoxOfficeList?.map {
                async {
                    val year = it.openDt.substring(0, 4)
                    val tmdbResponse = movieRemoteDatasource.searchMovie(query = it.movieNm, year = year)
                    val tmdbMovie = tmdbResponse.results?.firstOrNull()

                    Movie(
                        movieId = it.movieCd,
                        rank = it.rank,
                        openDt = it.openDt,
                        movieNm = it.movieNm,
                        audiInten = it.getAudienceIntensity(),
                        audiAcc = it.audiAcc,
                        posterPath = tmdbMovie?.poster_path,
                        voteAverage = tmdbMovie?.vote_average,
                        backdropPath = tmdbMovie?.backdrop_path,
                        overview = tmdbMovie?.overview
                    )
                }
            }

            this@MovieRepository.movieList = deferredList?.awaitAll()
            this@MovieRepository.movieList
        }
    }

    fun getMovie(movieId: String): Movie? {
        return movieList?.find { it.movieId == movieId }
    }
}