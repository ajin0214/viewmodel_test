package com.example.myapplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentMyMoviesBinding

class MyMoviesFragment : Fragment(R.layout.fragment_my_movies) {

    private val myMoviesAdapter: MyMoviesAdapter by lazy { MyMoviesAdapter() }
    private var _fragmentMyMoviesBinding: FragmentMyMoviesBinding? = null
    private val fragmentMyMoviesBinding get() = _fragmentMyMoviesBinding!!

    // ViewModel 초기화
    private val viewModel: MyMoviesViewModel by viewModels {
        MyMovieViewModelFactory((activity?.application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMyMoviesBinding = FragmentMyMoviesBinding.inflate(inflater, container, false)
        return fragmentMyMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMyMoviesBinding.rvMyMoviesList.adapter = myMoviesAdapter
        viewModel.allMyMovies.observe(viewLifecycleOwner) { myMovie ->
            myMoviesAdapter.submitList(myMovie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMyMoviesBinding = null
    }
}
