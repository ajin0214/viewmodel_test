package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MyMovie
import com.example.myapplication.data.MyMovieRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MyMoviesViewModel(private val repository: MyMovieRepository) : ViewModel() {
    val allMyMovies: LiveData<List<MyMovie>> = repository.allMyMovies.asLiveData()

    fun insert(myMovie: MyMovie) = viewModelScope.launch {
        repository.insert(myMovie)
    }
}

class MyMovieViewModelFactory(private val repository: MyMovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((MyMoviesViewModel::class.java))) {
            @Suppress("UNCHECKED_CAST")
            return MyMoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}