package com.example.myapplication.util

import android.app.Application
import android.content.Context

object ApplicationContextProvider {
    private var applicationInstance: Application? = null

    fun init(application: Application) {
        applicationInstance = application
    }

    fun getContext(): Context {
        return applicationInstance?.applicationContext
            ?: throw IllegalStateException("Application instance has not been initialized.")
    }
}
