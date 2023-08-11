package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.databinding.ItemBoxOfficeBinding


class BoxOfficeAdapter :
        RecyclerView.Adapter<BoxOfficeViewHolder>() {

    private var dailyBoxOfficeList: List<DailyBoxOfficeResult>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBoxOfficeBinding = ItemBoxOfficeBinding.inflate(inflater, parent, false)
        return BoxOfficeViewHolder(itemBoxOfficeBinding)
    }

    override fun onBindViewHolder(holder: BoxOfficeViewHolder, position: Int) {
        dailyBoxOfficeList?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return dailyBoxOfficeList?.size ?: 0
    }

    private class MyDiffCallback(
            private val oldList: List<DailyBoxOfficeResult>?,
            private val newList: List<DailyBoxOfficeResult>
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
            return oldItem == newItem
        }
    }

    fun updateList(dailyBoxOfficeList: List<DailyBoxOfficeResult>) {
        val diffCallback = MyDiffCallback(this.dailyBoxOfficeList, dailyBoxOfficeList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.dailyBoxOfficeList = dailyBoxOfficeList
        diffResult.dispatchUpdatesTo(this)


    }
}