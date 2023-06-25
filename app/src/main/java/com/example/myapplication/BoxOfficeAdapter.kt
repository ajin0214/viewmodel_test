package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BoxOfficeAdapter :
    RecyclerView.Adapter<BoxOfficeViewHolder>() {

    private var boxOfficeList: List<BoxOfficeDetailResult>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_box_office, parent, false)
        return BoxOfficeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BoxOfficeViewHolder, position: Int) {
        boxOfficeList?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return boxOfficeList?.size ?: 0
    }

    fun setBoxOfficeList(boxOfficeList: List<BoxOfficeDetailResult>) {
        this.boxOfficeList = boxOfficeList
    }
}