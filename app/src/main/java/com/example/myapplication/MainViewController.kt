package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class MainViewController(
    rootView: View,
    viewModelStoreOwner: ViewModelStoreOwner,
    private val viewLifecycleObserver: LifecycleOwner
) {
    private val boxofficelist: TextView by lazy { rootView.findViewById(R.id.boxoffice_list) }
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(viewModelStoreOwner)[MainViewModel::class.java] }

    fun init() {
        initObservers()
        initView()
    }

    private fun initView() {
    }

    private fun initObservers() {
        mainViewModel.searchDailyBoxOfficeList.observe(viewLifecycleObserver) {
            setBoxOfficeListText(text = it)
        }
    }
    private fun setBoxOfficeListText(text: String) {
        boxofficelist.text = text
    }
}