package com.example.myapplication.data.network

import com.example.myapplication.data.local.LocalMyMovie

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

fun NetworkMyMovie.toLocal() = LocalMyMovie(
    id = id,
    title = title,
    description = shortDescription,
    isWatched = (status == NetworkMyMovie.MyMovieStatus.COMPLETE),
)

fun List<NetworkMyMovie>.toLocal() = map { NetworkMyMovie::toLocal }

fun LocalMyMovie.toNetwork() = NetworkMyMovie(
    id = id,
    title = title,
    shortDescription = description,
    status = if (isWatched) {
        NetworkMyMovie.MyMovieStatus.COMPLETE
    } else {
        NetworkMyMovie.MyMovieStatus.ACTIVE
    }
)

fun List<LocalMyMovie>.toNetwork() = map { LocalMyMovie::toNetwork }