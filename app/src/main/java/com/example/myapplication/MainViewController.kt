package com.example.myapplication

import android.annotation.SuppressLint
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
    private val rvDailyBoxOfficeList: RecyclerView by lazy { rootView.findViewById(R.id.rv_box_office_list) }
    private val adapter: BoxOfficeAdapter by lazy { BoxOfficeAdapter() }

    fun init() {
        initView()
        initObservers()
    }

    private fun initView() {
        rvDailyBoxOfficeList.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
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