package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase


@Database(entities = [LocalMyMovie::class], version = 1, exportSchema = false)
abstract class MyMovieDatabase :RoomDatabase(){

    abstract fun myMovieDao() : MyMovieDao
}

