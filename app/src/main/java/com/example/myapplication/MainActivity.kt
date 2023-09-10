package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val mainFragment = MainFragment()
    private val myMoviesFragment = MyMoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 초기 프래그먼트 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, mainFragment, "MainFragment")
                .add(R.id.fragment_container, myMoviesFragment, "MyMoviesFragment")
                .hide(myMoviesFragment) // 초기에 MyMoviesFragment는 숨깁니다.
                .commit()
        }
// MyMoviesFragment로 이동하는 버튼
        val btnMyMovies: Button = findViewById(R.id.btn_my_movies)
        btnMyMovies.setOnClickListener {
            supportFragmentManager.popBackStackImmediate() // 백스택에서 DetailFragment 제거 (있을 경우)
            supportFragmentManager.beginTransaction()
                .hide(mainFragment)
                .show(myMoviesFragment)
                .commit()
        }

// MainFragment로 이동하는 버튼
        val btnMain: Button = findViewById(R.id.btn_main)
        btnMain.setOnClickListener {
            supportFragmentManager.popBackStackImmediate() // 백스택에서 DetailFragment 제거 (있을 경우)
            supportFragmentManager.beginTransaction()
                .hide(myMoviesFragment)
                .show(mainFragment)
                .commit()
        }
    }
}
