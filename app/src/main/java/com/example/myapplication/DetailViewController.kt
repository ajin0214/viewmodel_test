package com.example.myapplication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailViewController(
    private val binding: FragmentDetailBinding,
    viewModelStoreOwner: ViewModelStoreOwner,
    private val viewLifecycleOwner: LifecycleOwner
) {
    private val viewModel: DetailViewModel by lazy { ViewModelProvider(owner = viewModelStoreOwner)[DetailViewModel::class.java] }

    init {
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.btnSave.setOnClickListener {
            viewModel.onClickSave()
        }
    }

    private fun initObservers() {
        viewModel.movie.observe(viewLifecycleOwner) { it?.let { bindMovie(movie = it) } }
    }

    private fun bindMovie(movie: Movie) {
        with(binding) {
            backdrop.loadImage(imageUrl = movie.getBackDropPath())
            poster.loadImage(imageUrl = movie.getPosterUrl())
            title.text = movie.movieNm
            rating.text = movie.voteAverage.toString()
            overview.text = movie.overview
        }

    }
}