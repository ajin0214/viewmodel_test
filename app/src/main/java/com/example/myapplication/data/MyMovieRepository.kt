package com.example.myapplication.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MyMovieRepository(private val myMovieDao: MyMovieDao) {

    val allMyMovies: Flow<List<MyMovie>> = myMovieDao.observeAll()

    @WorkerThread
    suspend fun insert(movieId: String, title: String, description: String) {
        myMovieDao.insert(
            myMovie = MyMovie(
                movieId = movieId,
                title = title,
                description = description
            )
        )
    }
}