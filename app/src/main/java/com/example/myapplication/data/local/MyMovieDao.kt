package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MyMovieDao {

    @Query("SELECT * FROM myMovie")
    fun observeAll(): Flow<List<LocalMyMovie>>

    @Upsert
    suspend fun upsert(myMovie: LocalMyMovie)

    @Upsert
    suspend fun upsertAll(myMovies: List<LocalMyMovie>)

    @Query("UPDATE myMovie SET isWatched = :watched WHERE id = :myMovieID")
    suspend fun updateWatched(myMovieID: String, watched: Boolean)

    @Query("DELETE FROM myMovie")
    suspend fun deleteAll()
}