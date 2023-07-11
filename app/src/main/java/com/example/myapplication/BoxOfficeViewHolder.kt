package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) : RecyclerView.ViewHolder(itemBoxOfficeBinding.root)
{
    fun bind(boxOfficeDetailResult: BoxOfficeDetailResult) {
        itemBoxOfficeBinding.textRank.text = boxOfficeDetailResult.rank
        itemBoxOfficeBinding.textMovieTitle.text = boxOfficeDetailResult.movieNm
        itemBoxOfficeBinding.textOpenDate.text = boxOfficeDetailResult.openDt
    }
}