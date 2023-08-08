package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil



class BoxOfficeAdapter :
        RecyclerView.Adapter<BoxOfficeViewHolder>() {


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


    fun setBoxOfficeList(newBoxOfficeList: List<BoxOfficeDetailResult>) {
        val diffCallback = MyDiffCallback(boxOfficeList, newBoxOfficeList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        boxOfficeList = newBoxOfficeList
        diffResult.dispatchUpdatesTo(this)
    }

    private class MyDiffCallback(
            private val oldList: List<BoxOfficeDetailResult>?,
            private val newList: List<BoxOfficeDetailResult>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList?.size ?: 0
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList?.get(oldItemPosition)
            val newItem = newList[newItemPosition]
            return oldItem?.movieNm == newItem.movieNm // 고유한 아이템 식별자를 기준으로 비교
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList?.get(oldItemPosition)
            val newItem = newList[newItemPosition]
            return oldItem == newItem // 아이템의 내용이 동일한지 비교
        }
    }

    fun updateList(newBoxOfficeList: List<BoxOfficeDetailResult>) {
        val diffCallback = MyDiffCallback(boxOfficeList, newBoxOfficeList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        boxOfficeList = newBoxOfficeList
        diffResult.dispatchUpdatesTo(this)

    }
}