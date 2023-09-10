package com.example.myapplication.navigator


enum class Screens{
    MAIN,
    DETAIL,
    MYMOVIES
}

interface AppNavigator {
    fun navigateTo(screen: Screens)
}