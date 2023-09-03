package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.databinding.ItemBoxOfficeBinding

class BoxOfficeAdapter :
    ListAdapter<DailyBoxOfficeResult, BoxOfficeViewHolder>(DailyBoxOfficeDiffCallback) {

    companion object {
        val DailyBoxOfficeDiffCallback = object : DiffUtil.ItemCallback<DailyBoxOfficeResult>() {
            override fun areItemsTheSame(oldItem: DailyBoxOfficeResult, newItem: DailyBoxOfficeResult): Boolean {
                return oldItem.movieCd == newItem.movieCd
            }

            override fun areContentsTheSame(oldItem: DailyBoxOfficeResult, newItem: DailyBoxOfficeResult): Boolean {
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
