package com.example.myapplication.data

data class MyMovie(
    val id: String,
    val title: String = "",
    val description: String = "",
    val isWatched: Boolean = false
)