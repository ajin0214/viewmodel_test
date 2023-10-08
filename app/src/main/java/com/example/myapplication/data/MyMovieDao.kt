package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyMovieDao {
    @Query("SELECT * FROM myMovie")
    fun observeAll(): Flow<List<MyMovie>>

    @Insert
    suspend fun insert(myMovie: MyMovie)

    @Insert
    suspend fun insertAll(myMovies:List<MyMovie>)

    @Delete
    suspend fun delete(myMovie: MyMovie)

    @Query("DELETE FROM myMovie")
    suspend fun deleteAll()

}