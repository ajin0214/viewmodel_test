package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.util.ViewModelProviderHelper


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModelProviderHelper.createViewModel(
            owner = this,
            viewModel = MainViewModel(movieRepository = ServiceLocator.movieRepository)
        )

        MainViewController(
            fragmentMainBinding = binding,
            viewModelStoreOwner = this,
            viewLifecycleOwner = viewLifecycleOwner
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}