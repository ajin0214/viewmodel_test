package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {///싱글액티비티로 구성하기위해 프래그먼트로 변경해볼까나?

    private var _fragmentDetailBinding: FragmentDetailBinding? = null
    private val fragmentDetailBinding get() = _fragmentDetailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return fragmentDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieDetail = arguments?.getParcelable<DailyBoxOfficeResult>("movieDetail")

        val imageUrl = movieDetail?.backdropPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it } ?: R.drawable.default_image///이런비즈니스로직은 뷰모델로
        Glide.with(this)
            .load(imageUrl)
            .into(fragmentDetailBinding.backdrop)

        val imageUrl2 = movieDetail?.posterPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it } ?: R.drawable.default_image///이런비즈니스로직은 뷰모델로
        Glide.with(this)
            .load(imageUrl2)
            .into(fragmentDetailBinding.poster)

        fragmentDetailBinding.title.text = movieDetail?.movieNm
        fragmentDetailBinding.rating.rating = movieDetail?.voteAverage?.toFloat() ?: 0f
        fragmentDetailBinding.overview.text = movieDetail?.overview
    }

    ///채워넣는 애들은 디테일컨트롤러에서
    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDetailBinding = null
    }
}