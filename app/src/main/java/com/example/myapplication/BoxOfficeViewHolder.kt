package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) : RecyclerView.ViewHolder(itemBoxOfficeBinding.root)
{
    fun bind(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        itemBoxOfficeBinding.textRank.text = dailyBoxOfficeResult.rank
        itemBoxOfficeBinding.textMovieTitle.text = dailyBoxOfficeResult.movieNm
        itemBoxOfficeBinding.textOpenDate.text = dailyBoxOfficeResult.openDt
    }
}