package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.MyMovie
import com.example.myapplication.databinding.ItemMyMovieBinding

class MyMoviesViewHolder(private val binding: ItemMyMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MyMovie) {
        binding.tvMovieTitle.text = movie.title
        binding.tvMovieDescription.text = movie.description
    }
}
