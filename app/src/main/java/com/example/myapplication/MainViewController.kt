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
        mainViewModel.dailyBoxOfficeList.observe(viewLifecycleObserver) {
            with(adapter) {
                setDailyBoxOfficeList(dailyBoxOfficeList = it)
                notifyDataSetChanged()
            }

        }
    }
}