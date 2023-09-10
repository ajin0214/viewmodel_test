package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.MyMovie
import com.example.myapplication.data.network.NetworkMyMovie


@Entity(
    tableName = "myMovie"
)
data class LocalMyMovie(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val isWatched: Boolean
)

fun LocalMyMovie.toExternal() = MyMovie(
    id = id,
    title = title,
    description = description,
    isWatched = isWatched,
)

fun List<LocalMyMovie>.toExternal() = map(LocalMyMovie::toExternal)

fun MyMovie.toLocal() = LocalMyMovie(
    id = id,
    title = title,
    description = description,
    isWatched = isWatched
)

fun NetworkMyMovie.toLocal() = LocalMyMovie(
    id = id,
    title = title,
    description = shortDescription,
    isWatched = (status == NetworkMyMovie.MyMovieStatus.COMPLETE),
)

fun List<NetworkMyMovie>.toLocal() = map { it.toLocal() }

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

fun List<LocalMyMovie>.toNetwork() = map { it.toNetwork() }
