package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding
import com.bumptech.glide.Glide

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) :
    RecyclerView.ViewHolder(itemBoxOfficeBinding.root)
{
    fun bind(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        with(itemBoxOfficeBinding){
            textRank.text = dailyBoxOfficeResult.rank
            textMovieTitle.text = dailyBoxOfficeResult.movieNm
            textOpenDate.text = dailyBoxOfficeResult.openDt

            val imageUrl = dailyBoxOfficeResult.posterPath?.let{"https://image.tmdb.org/t/p/w500$it"}
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(imagePoster)
        }
    }
}