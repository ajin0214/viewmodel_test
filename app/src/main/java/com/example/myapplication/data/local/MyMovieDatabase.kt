package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase


@Entity
data class BlankEntity (
    @PrimaryKey val id: String,
)


@Database(entities = [LocalMyMovie::class], version = 1, exportSchema = false)
abstract class MyMovieDatabase :RoomDatabase(){

    abstract fun myMovieDao() : MyMovieDao
}


/**
 * The Room Database that contains the Task table.
 *
 * Note that exportSchema should be true in production databases.
 */
