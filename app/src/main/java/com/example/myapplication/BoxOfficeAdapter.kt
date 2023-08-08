package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BoxOfficeAdapter : RecyclerView.Adapter<BoxOfficeViewHolder>() {

    private var dailyBoxOfficeList: List<DailyBoxOfficeResult>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_box_office, parent, false)
        return BoxOfficeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BoxOfficeViewHolder, position: Int) {
        dailyBoxOfficeList?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return dailyBoxOfficeList?.size ?: 0
    }

    fun setDailyBoxOfficeList(dailyBoxOfficeList: List<DailyBoxOfficeResult>) {
        this.dailyBoxOfficeList = dailyBoxOfficeList
    }
}