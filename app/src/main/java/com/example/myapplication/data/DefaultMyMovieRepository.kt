package com.example.myapplication.data

import com.example.myapplication.data.local.MyMovieDao
import com.example.myapplication.data.local.toExternal
import com.example.myapplication.data.local.toLocal
import com.example.myapplication.data.local.toNetwork
import com.example.myapplication.data.network.MyMovieNetworkDataSource
import com.example.myapplication.di.ApplicationScope
import com.example.myapplication.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMyMovieRepository @Inject constructor(
    private val localDataSource: MyMovieDao,
    private val networkDataSource: MyMovieNetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope
) {
    fun observeAll(): Flow<List<MyMovie>> {
        return localDataSource.observeAll().map { myMovies ->
            myMovies.toExternal()
        }
    }

    suspend fun create(title: String, description: String): String {
        val myMovieId = withContext(dispatcher) {
            createMyMovieId()
        }
        val myMovie = MyMovie(
            title = title,
            description = description,
            id = myMovieId
        )
        localDataSource.upsert(myMovie.toLocal())
        saveMyMoviesToNetwork()
        return myMovieId
    }



    suspend fun complete(myMovieId: String) {
        localDataSource.updateWatched(myMovieId, true)
        saveMyMoviesToNetwork()
    }

    suspend fun refresh() {
        val networkMyMovies = networkDataSource.loadMyMovies()
        localDataSource.deleteAll()
        val localMyMovies = withContext(dispatcher) {
            networkMyMovies.toLocal()
        }
        localDataSource.upsertAll(networkMyMovies.toLocal())
    }

    private fun saveMyMoviesToNetwork() {
        scope.launch {
            val localMyMovies = localDataSource.observeAll().first()
            val networkMyMovies = withContext(dispatcher) {
                localMyMovies.toNetwork()
            }
            networkDataSource.saveMyMovies(networkMyMovies)
        }
    }

    private fun createMyMovieId(): String {
        return UUID.randomUUID().toString()
    }
}