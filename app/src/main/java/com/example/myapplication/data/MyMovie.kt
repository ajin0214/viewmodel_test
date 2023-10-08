package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyMovie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie_id") val movieId: String,
    @ColumnInfo(name = "movie_name") val title: String,
    @ColumnInfo(name = "description") val description: String,
)
