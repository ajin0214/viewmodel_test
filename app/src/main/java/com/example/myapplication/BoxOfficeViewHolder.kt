package com.example.myapplication

import Constants
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding
import com.bumptech.glide.Glide

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) :
    RecyclerView.ViewHolder(itemBoxOfficeBinding.root) {
    fun bind(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        setViewHolderData(dailyBoxOfficeResult)
        setOnClickListener(dailyBoxOfficeResult)
    }

    private fun setTextViewText(textView: TextView, text: String?) {
        textView.text = text
    }

    private fun getPosterUrl(dailyBoxOfficeResult: DailyBoxOfficeResult): String {
        return dailyBoxOfficeResult.posterPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it }.toString()
    }

    private fun loadImage(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context).load(imageUrl).into(imageView)
    }

    private fun setViewHolderData(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        with(itemBoxOfficeBinding) {
            setTextViewText(textRank, dailyBoxOfficeResult.rank)
            setTextViewText(textMovieTitle, dailyBoxOfficeResult.movieNm)
            setTextViewText(textOpenDate, itemView.context.getString(R.string.movie_release_date, dailyBoxOfficeResult.openDt))
            setTextViewText(textAudienceCount, itemView.context.getString(R.string.movie_audience_count, dailyBoxOfficeResult.audiAcc))
            setTextViewText(textAudienceIntensity, getAudienceIntensity(dailyBoxOfficeResult))
            loadImage(imagePoster, getPosterUrl(dailyBoxOfficeResult))
        }
    }

    private fun getAudienceIntensity(dailyBoxOfficeResult: DailyBoxOfficeResult): String {
        return if (dailyBoxOfficeResult.audiAcc == dailyBoxOfficeResult.audiCnt) {
            itemView.context.getString(R.string.movie_new)
        } else {
            itemView.context.getString(R.string.movie_audience_intensity, dailyBoxOfficeResult.audiInten)
        }
    }

    private fun setOnClickListener(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        itemView.setOnClickListener {
            val activity = it.context as? AppCompatActivity
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container, DetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("movieDetail", dailyBoxOfficeResult)
                    }
                })
                addToBackStack(null)
            }
        }
    }
}


