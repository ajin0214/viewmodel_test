package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyMovie::class], version = 1, exportSchema = false)
abstract class MyMovieLocalDatabase : RoomDatabase() {
    abstract fun myMovieDao(): MyMovieDao

    companion object {
        @Volatile
        private var INSTANCE: MyMovieLocalDatabase? = null
        fun getDatabase(
            context: Context
        ): MyMovieLocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyMovieLocalDatabase::class.java,
                    "myMovie_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}