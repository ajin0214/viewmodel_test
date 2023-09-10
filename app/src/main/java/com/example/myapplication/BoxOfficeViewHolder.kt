package com.example.myapplication

import Constants
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding
import com.bumptech.glide.Glide
import com.example.myapplication.navigator.AppNavigator
import com.example.myapplication.navigator.Screens
import javax.inject.Inject

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) :
    RecyclerView.ViewHolder(itemBoxOfficeBinding.root) {

    @Inject
    lateinit var navigator: AppNavigator
    fun bind(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        setViewHolderData(dailyBoxOfficeResult)
        setOnClickListener()
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

    private fun setOnClickListener() {
        itemView.setOnClickListener {
            navigator.navigateTo(Screens.DETAIL)
        }
    }
}
