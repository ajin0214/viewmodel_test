package com.example.myapplication.navigator


enum class Screens{
    MAIN,
    DETAIL
}

interface AppNavigator {
    fun navigateTo(screen: Screens)
}