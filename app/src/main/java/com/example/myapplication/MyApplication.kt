package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.MyMovieLocalDatabase
import com.example.myapplication.data.MyMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MyMovieLocalDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { MyMovieRepository(database.myMovieDao()) }
}