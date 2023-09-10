package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.example.myapplication.DailyBoxOfficeResult
import com.example.myapplication.R
import com.example.myapplication.data.DefaultMyMovieRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    @Inject
    lateinit var dmmRepository: DefaultMyMovieRepository
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

        val imageUrl = movieDetail?.backdropPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it } ?: R.drawable.default_image//뷰모델로
        Glide.with(this)
            .load(imageUrl)
            .into(fragmentDetailBinding.backdrop)

        val imageUrl2 = movieDetail?.posterPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it } ?: R.drawable.default_image//뷰모델로
        Glide.with(this)
            .load(imageUrl2)
            .into(fragmentDetailBinding.poster)

        fragmentDetailBinding.title.text = movieDetail?.movieNm
        fragmentDetailBinding.rating.text = movieDetail?.voteAverage.toString()
        fragmentDetailBinding.overview.text = movieDetail?.overview

        val descriptionEditText = fragmentDetailBinding.etDescription
        val saveButton = fragmentDetailBinding.btnSave

        saveButton.setOnClickListener {
            val titleText = fragmentDetailBinding.title.text.toString()
            val descriptionText = descriptionEditText.text.toString()
            lifecycleScope.launch {
                val result = dmmRepository.create(titleText, descriptionText)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDetailBinding = null
    }
}