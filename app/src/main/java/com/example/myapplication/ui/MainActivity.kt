package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.navigator.AppNavigator
import com.example.myapplication.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: AppNavigator
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.MAIN)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}