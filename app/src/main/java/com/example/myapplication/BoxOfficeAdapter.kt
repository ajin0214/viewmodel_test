package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.databinding.ItemBoxOfficeBinding

class BoxOfficeAdapter :
        RecyclerView.Adapter<BoxOfficeViewHolder>() {

    private var boxOfficeList: List<BoxOfficeDetailResult>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBoxOfficeBinding = ItemBoxOfficeBinding.inflate(inflater, parent, false)
        return BoxOfficeViewHolder(itemBoxOfficeBinding)
    }

    override fun onBindViewHolder(holder: BoxOfficeViewHolder, position: Int) {
        boxOfficeList?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return boxOfficeList?.size ?: 0
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
            return oldItem?.movieCd == newItem.movieCd // 고유한 아이템 식별자를 기준으로 비교
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