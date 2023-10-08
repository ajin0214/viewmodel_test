package com.example.myapplication.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MyMovieRepository(private val myMovieDao: MyMovieDao) {

    val allMyMovies: Flow<List<MyMovie>> = myMovieDao.observeAll()

    @WorkerThread
    suspend fun insert(myMovie: MyMovie) {
        myMovieDao.insert(myMovie)
    }
}