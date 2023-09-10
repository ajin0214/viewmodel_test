package com.example.myapplication.data.network

data class NetworkMyMovie(
    val id: String,
    val title: String,
    val shortDescription: String,
    val priority: Int? = null,
    val status: MyMovieStatus = MyMovieStatus.ACTIVE
) {
    enum class MyMovieStatus {
        ACTIVE,
        COMPLETE
    }
}
