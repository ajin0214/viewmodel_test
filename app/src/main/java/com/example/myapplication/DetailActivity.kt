package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDetailsBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val movieDetail = intent.getParcelableExtra<DailyBoxOfficeResult>("movieDetail")//getParcelableExtra은 deperecated라는데 일단pr해영

        val imageUrl = movieDetail?.backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: R.drawable.default_image
        Glide.with(this)
            .load(imageUrl)
            .into(activityDetailBinding.backdrop)


        val imageUrl2 = movieDetail?.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: R.drawable.default_image
        Glide.with(this)
            .load(imageUrl2)
            .into(activityDetailBinding.poster)

        activityDetailBinding.title.text = movieDetail?.movieNm
        activityDetailBinding.rating.rating = movieDetail?.voteAverage?.toFloat() ?: 0f
        activityDetailBinding.overview.text = movieDetail?.overview

    }
}
