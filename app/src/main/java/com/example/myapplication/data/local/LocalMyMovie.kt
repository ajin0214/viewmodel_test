package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.MyMovie


@Entity(
    tableName = "myMovie"
)
data class LocalMyMovie(
    @PrimaryKey val id: String,
    val title: String = "",
    val description: String = "",
    val isWatched: Boolean = false
)

fun LocalMyMovie.toExternal() = MyMovie(
    id = id,
    title = title,
    descripton = description,
    isWatched = isWatched,
)

fun List<LocalMyMovie>.toExternal() = map(LocalMyMovie::toExternal)

fun MyMovie.toLocal() = LocalMyMovie(
    id = id,
    title = title,
    description = descripton,
    isWatched = isWatched
)
