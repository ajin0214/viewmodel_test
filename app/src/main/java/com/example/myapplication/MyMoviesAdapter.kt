package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class MyMovie(val title: String, val description: String)
class MyMoviesAdapter(private val myMovies: List<MyMovie>) : RecyclerView.Adapter<MyMoviesAdapter.MyMovieViewHolder>() {

    inner class MyMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_movie_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_movie_description)

        fun bind(myMovie: MyMovie) {
            titleTextView.text = myMovie.title
            descriptionTextView.text = myMovie.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_movie, parent, false)
        return MyMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyMovieViewHolder, position: Int) {
        holder.bind(myMovies[position])
    }

    override fun getItemCount() = myMovies.size
}
