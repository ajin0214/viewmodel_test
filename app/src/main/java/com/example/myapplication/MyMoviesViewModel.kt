package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication.data.MyMovie
import com.example.myapplication.data.MyMovieRepository

class MyMoviesViewModel(repository: MyMovieRepository) : ViewModel() {
    val allMyMovies: LiveData<List<MyMovie>> = repository.allMyMovies.asLiveData()
}