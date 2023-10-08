package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.databinding.ItemBoxOfficeBinding

class BoxOfficeAdapter :
    ListAdapter<Movie, BoxOfficeViewHolder>(DailyBoxOfficeDiffCallback) {

    companion object {
        private val DailyBoxOfficeDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBoxOfficeBinding = ItemBoxOfficeBinding.inflate(inflater, parent, false)
        return BoxOfficeViewHolder(itemBoxOfficeBinding)
    }

    override fun onBindViewHolder(holder: BoxOfficeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
