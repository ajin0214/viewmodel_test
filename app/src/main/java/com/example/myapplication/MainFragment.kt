package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _fragmentMainBinding: FragmentMainBinding? = null
    private val fragmentMainBinding get() = _fragmentMainBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return fragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainViewController(
            fragmentMainBinding = fragmentMainBinding,
            viewModelStoreOwner = this,
            viewLifecycleObserver = viewLifecycleOwner
        ).init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMainBinding = null
    }
}