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
        activityMainBinding.rvBoxofficeList.adapter = adapter
    }

    private fun initObservers() {
        mainViewModel.searchDailyBoxOfficeList.observe(viewLifecycleObserver) { newBoxOfficeList ->
            adapter.updateList(newBoxOfficeList)
        }
    }
/*
    private fun setInitialBoxOfficeList() {
        val initialBoxOfficeList = // 초기 목록 데이터
                adapter.setBoxOfficeList(initialBoxOfficeList)
    }
 */
}