package com.example.myapplication.data.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class MyMovieNetworkDataSource @Inject constructor() {

    // A mutex is used to ensure that reads and writes are thread-safe.
    private val accessMutex = Mutex()
    private var myMovies = listOf(
        NetworkMyMovie(
            id = "PISA",
            title = "Build tower in Pisa",
            shortDescription = "Ground looks good, no foundation work required."
        ),
        NetworkMyMovie(
            id = "TACOMA",
            title = "Finish bridge in Tacoma",
            shortDescription = "Found awesome girders at half the cost!"
        )
    )

    suspend fun loadMyMovies(): List<NetworkMyMovie> = accessMutex.withLock {
        delay(SERVICE_LATENCY_IN_MILLIS)
        return myMovies
    }

    suspend fun saveMyMovies(newMyMovies: List<NetworkMyMovie>) = accessMutex.withLock {
        delay(SERVICE_LATENCY_IN_MILLIS)
        myMovies = newMyMovies
    }
}

private const val SERVICE_LATENCY_IN_MILLIS = 2000L