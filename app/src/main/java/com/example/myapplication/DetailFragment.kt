package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.example.myapplication.data.MyMovie
import com.example.myapplication.util.hideKeyboard

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _fragmentDetailBinding: FragmentDetailBinding? = null
    private val fragmentDetailBinding get() = _fragmentDetailBinding!!
    private val viewModel: MyMoviesViewModel by viewModels {
        MyMovieViewModelFactory((activity?.application as MyApplication).repository)
    }

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

        view.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                v.performClick()
            }
            hideKeyboard(requireActivity())
            false
        }

        val movieDetail = arguments?.getParcelable<DailyBoxOfficeResult>("movieDetail")

        val imageUrl = movieDetail?.backdropPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it } ?: R.drawable.default_image
        Glide.with(this)
            .load(imageUrl)
            .into(fragmentDetailBinding.backdrop)

        val imageUrl2 = movieDetail?.posterPath?.let { Constants.TMDB_POSTER_IMAGE_URL + it } ?: R.drawable.default_image
        Glide.with(this)
            .load(imageUrl2)
            .into(fragmentDetailBinding.poster)

        fragmentDetailBinding.title.text = movieDetail?.movieNm
        fragmentDetailBinding.rating.text = movieDetail?.voteAverage?.toString()
        fragmentDetailBinding.overview.text = movieDetail?.overview
        fragmentDetailBinding.btnSave.setOnClickListener {
            val description = fragmentDetailBinding.etDescription.text.toString()
            val title = fragmentDetailBinding.title.text.toString()
            val myMovie = MyMovie(title = title, description = description)
            viewModel.insert(myMovie)
            activity?.supportFragmentManager?.popBackStack()
            (activity as? MainActivity)?.switchToMyMoviesFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDetailBinding = null
    }
}