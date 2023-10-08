package com.example.myapplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMyMoviesBinding
import com.example.myapplication.util.ViewModelProviderHelper

class MyMoviesFragment : Fragment(R.layout.fragment_my_movies) {

    private val myMoviesAdapter: MyMoviesAdapter by lazy { MyMoviesAdapter() }
    private var _binding: FragmentMyMoviesBinding? = null
    private val binding get() = _binding!!

    private var viewModel: MyMoviesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviderHelper.createViewModel(
            owner = this,
            viewModel = MyMoviesViewModel(ServiceLocator.myMovieMovieRepository)
        )

        initViews()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        binding.rvMyMoviesList.adapter = myMoviesAdapter
    }

    private fun initObservers() {
        viewModel?.let {
            it.allMyMovies.observe(viewLifecycleOwner) { allMyMovies ->
                myMoviesAdapter.submitList(allMyMovies)
            }
        }
    }
}
