package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoxOfficeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textRank: TextView = itemView.findViewById(R.id.text_rank)
    private val textMovieTitle: TextView = itemView.findViewById(R.id.text_movie_title)
    private val textOpenDate: TextView = itemView.findViewById(R.id.text_open_date)

    fun bind(boxOfficeDetailResult: BoxOfficeDetailResult) {
        textRank.text = boxOfficeDetailResult.rank
        textMovieTitle.text = boxOfficeDetailResult.movieNm
        textOpenDate.text = boxOfficeDetailResult.openDt
    }
}