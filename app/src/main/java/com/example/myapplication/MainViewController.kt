package com.example.myapplication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.myapplication.databinding.ActivityMainBinding

class MainViewController(
    private val activityMainBinding: ActivityMainBinding,
    private val viewModelStoreOwner: ViewModelStoreOwner,
    private val viewLifecycleObserver: LifecycleOwner
) {
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(viewModelStoreOwner)[MainViewModel::class.java] }
    private val adapter: BoxOfficeAdapter by lazy { BoxOfficeAdapter() }

    fun init() {
        initView()
        initObservers()
    }

    private fun initView() {
        activityMainBinding.rvDailyBoxOfficeList.adapter = adapter
    }

    private fun initObservers() {
        mainViewModel.dailyBoxOfficeList.observe(viewLifecycleObserver) { dailyBoxOfficeList ->
            adapter.submitList(dailyBoxOfficeList)
        }
    }
}