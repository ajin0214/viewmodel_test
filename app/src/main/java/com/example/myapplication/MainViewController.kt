
package com.example.myapplication

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import okhttp3.*
import java.io.IOException
import android.os.Handler
import android.os.Looper


class MainViewController(
    rootView: View,
    viewModelStoreOwner: ViewModelStoreOwner,
    private val viewLifecycleObserver: LifecycleOwner
) {

    private val tvClickButton: TextView by lazy { rootView.findViewById(R.id.tv_click_button) }
    private val tvGoodbye: TextView by lazy { rootView.findViewById(R.id.tv_goodbye) }
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(viewModelStoreOwner).get(MainViewModel::class.java) }
    private val client = OkHttpClient()

    fun init() {
        initObservers()
        initView()


        val request = Request.Builder()
            .url("https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=9bd03980caf9c2e3af6dd832245794cf&targetDt=20230501")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()


                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    val tvGoodbye: TextView = findViewById(R.id.tv_goodbye)
                    tvGoodbye.text = responseBody
                }
            }
        })
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
    }

    private fun onClickTvClickButton() {
        mainViewModel.onClickTvClickButton()
    }

    private fun setHelloWorldVisibility(isVisible: Boolean) {
        tvClickButton.visibility = if (isVisible) VISIBLE else INVISIBLE
    }
}
