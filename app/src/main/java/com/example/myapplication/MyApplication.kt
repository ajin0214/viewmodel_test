package com.example.myapplication

import android.app.Application
import com.example.myapplication.util.ApplicationContextProvider

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ApplicationContextProvider.init(application = this)
    }
}