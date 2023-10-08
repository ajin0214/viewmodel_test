package com.example.myapplication

import com.example.myapplication.data.MyMovieLocalDatabase
import com.example.myapplication.data.MyMovieRepository
import com.example.myapplication.data.movie.MovieRepository
import com.example.myapplication.data.movie.source.remote.MovieRemoteDatasource
import com.example.myapplication.data.movie.source.remote.kobis.KobisRetrofitInstance
import com.example.myapplication.data.movie.source.remote.tmdb.TmdbRetrofitInstance
import com.example.myapplication.util.ApplicationContextProvider

object ServiceLocator {
    val movieRepository: MovieRepository by lazy {
        MovieRepository(
            movieRemoteDatasource = MovieRemoteDatasource(
                kobisApiService = KobisRetrofitInstance.createKobisApiService(),
                tmdbApiService = TmdbRetrofitInstance.createTmdbApiService()
            )
        )
    }

    private val database by lazy { MyMovieLocalDatabase.getDatabase(ApplicationContextProvider.getContext()) }
    val myMovieMovieRepository: MyMovieRepository by lazy { MyMovieRepository(myMovieDao = database.myMovieDao()) }
}