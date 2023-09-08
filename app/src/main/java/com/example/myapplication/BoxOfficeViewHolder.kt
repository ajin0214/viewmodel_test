package com.example.myapplication

import Constants
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBoxOfficeBinding
import com.bumptech.glide.Glide

class BoxOfficeViewHolder(private val itemBoxOfficeBinding: ItemBoxOfficeBinding) :
    RecyclerView.ViewHolder(itemBoxOfficeBinding.root) {
    fun bind(dailyBoxOfficeResult: DailyBoxOfficeResult) {
        with(itemBoxOfficeBinding) {
            textRank.text = dailyBoxOfficeResult.rank
            textMovieTitle.text = dailyBoxOfficeResult.movieNm
            textOpenDate.text = itemView.context.getString(R.string.movie_release_date, dailyBoxOfficeResult.openDt)
            textAudienceCount.text = itemView.context.getString(R.string.movie_audience_count, dailyBoxOfficeResult.audiAcc)

            if (dailyBoxOfficeResult.audiAcc == dailyBoxOfficeResult.audiCnt) {
                textAudienceIntensity.text = itemView.context.getString(R.string.movie_new)
            } else {
                textAudienceIntensity.text = itemView.context.getString(R.string.movie_audience_intensity)
            }
            /////ifelse 로직은 뷰모델에서 정의하고 여기서는 데이터만 넣는식으로

            val imageUrl = dailyBoxOfficeResult.posterPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it }///url주소도 다른곳에 넣어두고 필요할때마다 꺼내쓰기//디미터법칙
            ///이것도 뷰모델로 보내버려이잇
            Glide.with(itemView.context)///글라이드는 뷰익스텐션에 이미지뷰
                .load(imageUrl)
                .into(imagePoster)
            ///데이터를 채우는 함수들
            itemView.setOnClickListener {
                /*콘텍스트도 뷰모델에서 어떻게 가져오지?*///안드로이드뷰모델?콘텍스트를 뷰모델에서 제공해주는 뷰모델orDI라이브러리, 전역util함수
                val bundle = Bundle()
                val movieDetail = dailyBoxOfficeResult
                bundle.putParcelable("movieDetail", movieDetail)
                it.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
                ////여기 비지니스로직도 ui단에는 없도록//뷰컨트롤러 거쳐서 뷰모델로 보내기
                 // currentItem은 현재 항목의 데이터입니다.
                /////listener 관련함수들
                ///기능이 다르니까 함수를 분리해서 따로 넣어주자.
            }
        }
    }
}