package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [MyMovie::class], version = 1, exportSchema = false)
abstract class MyMovieLocalDatabase : RoomDatabase() {
    abstract fun myMovieDao(): MyMovieDao

    private class MyMovieDataBaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var myMovieDao = database.myMovieDao()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MyMovieLocalDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MyMovieLocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyMovieLocalDatabase::class.java,
                    "myMovie_database"
                )
                    .addCallback(MyMovieDataBaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}