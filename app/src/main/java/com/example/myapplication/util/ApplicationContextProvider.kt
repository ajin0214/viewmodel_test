package com.example.myapplication.util

import android.content.Context
import android.app.Application

class ApplicationContextProvider {
    companion object {
        private var applicationInstance: Application? = null

        fun init(application: Application) {
            applicationInstance = application
        }

        fun getContext(): Context {
            return applicationInstance?.applicationContext
                ?: throw IllegalStateException("Application instance has not been initialized.")
        }
    }
}