package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.util.Constants.MOVIE_ID
import com.example.myapplication.util.ViewModelProviderHelper

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var viewModel: DetailViewModel? = null
    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.movieId = arguments?.getString(MOVIE_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviderHelper.createViewModel(
            owner = this,
            viewModel = DetailViewModel(
                movieId = movieId,
                movieRepository = ServiceLocator.movieRepository,
                myMovieRepository = ServiceLocator.myMovieMovieRepository
            )
        )

        DetailViewController(
            binding = binding,
            viewModelStoreOwner = this,
            viewLifecycleOwner = viewLifecycleOwner
        )

        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel?.let {
            it.goToMyMoviesFragment.observe(viewLifecycleOwner) {
                if (isAdded) {
                    parentFragmentManager.popBackStack()
                    (activity as? MainActivity)?.switchToMyMoviesFragment()
                }
            }
        }
    }
}