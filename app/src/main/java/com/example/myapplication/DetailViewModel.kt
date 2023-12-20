package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MyMovieRepository
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.data.movie.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieId: String?,
    private val movieRepository: MovieRepository,
    private val myMovieRepository: MyMovieRepository
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _goToMyMoviesFragment = MutableLiveData<Unit>()
    val goToMyMoviesFragment: LiveData<Unit> = _goToMyMoviesFragment

    init {
        initMovie()
    }

    private fun initMovie() {
        val movie = movieId?.let { movieRepository.getMovie(movieId = it) }
        movie?.let { updateMovie(movie = it) }
    }

    private fun updateMovie(movie: Movie) {
        _movie.value = movie
    }

    fun onClickSave() {
        saveMyMovie()
        goToMyMoviesFragment()
    }

    private fun goToMyMoviesFragment() {
        _goToMyMoviesFragment.value = Unit
    }

    private fun saveMyMovie() {
        viewModelScope.launch {
            val movie = _movie.value ?: return@launch
            with(movie) {
                if (movieId == null || movieNm == null || overview == null) {
                    return@launch
                }
                myMovieRepository.insert(
                    movieId = movieId,
                    title = movieNm,
                    description = overview
                )
            }
        }
    }
}