package com.example.myapplication

data class MyMovie(
    val id: String,
    val title: String = "",
    val descripton: String = "",
    val isWatched: Boolean = false
) {}
