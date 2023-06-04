package com.example.myapplication

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class MainViewController(
    rootView: View,
    viewModelStoreOwner: ViewModelStoreOwner,
    private val viewLifecycleObserver: LifecycleOwner
) {

    private val tvClickButton: TextView by lazy { rootView.findViewById(R.id.tv_click_button) }
    private val tvGoodbye: TextView by lazy { rootView.findViewById(R.id.tv_goodbye) }
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(viewModelStoreOwner)[MainViewModel::class.java] }

    fun init() {
        initObservers()
        initView()
    }

    private fun initView() {
        tvClickButton.setOnClickListener {
            onClickTvClickButton()
        }
    }

    private fun initObservers() {
        mainViewModel.isHelloWorldVisible.observe(viewLifecycleObserver) { isVisible ->
            setHelloWorldVisibility(isVisible)
        }
        mainViewModel.searchDailyBoxOfficeList.observe(viewLifecycleObserver) {
            setGoodByeText(text = it)
        }
    }

    private fun onClickTvClickButton() {
        mainViewModel.onClickTvClickButton()
    }

    private fun setHelloWorldVisibility(isVisible: Boolean) {
        tvClickButton.visibility = if (isVisible) VISIBLE else INVISIBLE
    }

    private fun setGoodByeText(text: String) {
        tvGoodbye.text = text
    }
}