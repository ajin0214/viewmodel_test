package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.MyMovie
import com.example.myapplication.databinding.ItemMyMovieBinding

class MyMoviesAdapter :
    ListAdapter<MyMovie, MyMoviesViewHolder>(MyMoviesComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMoviesViewHolder {
        val binding = ItemMyMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val MyMoviesComparator = object : DiffUtil.ItemCallback<MyMovie>() {
            override fun areItemsTheSame(oldItem: MyMovie, newItem: MyMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MyMovie, newItem: MyMovie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
