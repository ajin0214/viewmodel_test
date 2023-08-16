package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) :
    RecyclerView.ViewHolder(itemBoxOfficeBinding.root)
{
    fun bind(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        with(itemBoxOfficeBinding){
            textRank.text = dailyBoxOfficeResult.rank
            textMovieTitle.text = dailyBoxOfficeResult.movieNm
            textOpenDate.text = dailyBoxOfficeResult.openDt}
    }
}