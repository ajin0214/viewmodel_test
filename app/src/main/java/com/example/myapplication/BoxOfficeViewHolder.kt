package com.example.myapplication

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding
import com.bumptech.glide.Glide

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) :
    RecyclerView.ViewHolder(itemBoxOfficeBinding.root) {
    fun bind(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        with(itemBoxOfficeBinding) {
            textRank.text = dailyBoxOfficeResult.rank
            textMovieTitle.text = dailyBoxOfficeResult.movieNm
            textOpenDate.text = "영화 개봉 : ${dailyBoxOfficeResult.openDt}"///여기보면 string이랑 변수랑 같이 ""안에 넣는게 메모리많이 먹는대서 다음pr때 다른방식으로 수정해놓겟음.밑에애들까지쭉다전부
            textAudienceCount.text = "누적 관람객 : ${dailyBoxOfficeResult.audiCnt}"

            if (dailyBoxOfficeResult.audiCnt == dailyBoxOfficeResult.audiInten) {
                textAudienceIntensity.text = "new!"
            } else {
                textAudienceIntensity.text = "관람객 증감 : ${dailyBoxOfficeResult.audiInten}"//변수입력이 조금 잘못된것같아서 이건 다음pr때 수정해놓겟음
            }

            val imageUrl = dailyBoxOfficeResult.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(imagePoster)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("movieDetail", dailyBoxOfficeResult)
                itemView.context.startActivity(intent)
            }
        }
    }
}