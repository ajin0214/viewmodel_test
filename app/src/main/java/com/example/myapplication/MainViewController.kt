package com.example.myapplication

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

class MainViewController(
    rootView: View,
    viewModelStoreOwner: ViewModelStoreOwner,
    private val viewLifecycleObserver: LifecycleOwner
) {
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(viewModelStoreOwner)[MainViewModel::class.java] }
    private val boxOfficeList: RecyclerView by lazy { rootView.findViewById(R.id.rv_boxoffice_list) }
    private val adapter: BoxOfficeAdapter by lazy { BoxOfficeAdapter() }

    fun init() {
        initView()
        initObservers()
    }

    private fun initView() {
        boxOfficeList.adapter = adapter
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