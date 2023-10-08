package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.databinding.ItemBoxOfficeBinding
import com.example.myapplication.util.Constants.MOVIE_ID
import com.example.myapplication.util.ResourceUtils.getString

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) :
    RecyclerView.ViewHolder(itemBoxOfficeBinding.root) {
    fun bind(movie: Movie) {
        setViewHolderData(movie = movie)
        setOnClickListener(movie = movie)
    }

    private fun setViewHolderData(movie: Movie) {
        with(itemBoxOfficeBinding) {
            textRank.text = movie.rank
            textMovieTitle.text = movie.movieNm
            textOpenDate.text = getString(R.string.movie_release_date, movie.openDt ?: "")
            textAudienceCount.text = getString(R.string.movie_audience_count, movie.audiAcc ?: "")
            textAudienceIntensity.text = movie.audiInten
            imagePoster.loadImage(movie.getPosterUrl())
        }
    }

    private fun setOnClickListener(movie: Movie) {
        itemView.setOnClickListener {
            val activity = it.context as? AppCompatActivity
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, DetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(MOVIE_ID, movie.movieId)
                    }
                })
                addToBackStack(null)
            }
        }
    }
}


