package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        MainViewController(
            activityMainBinding = activityMainBinding,
            viewModelStoreOwner = this,
            viewLifecycleObserver = this
        ).init()

        ViewModelProvider(this)[MainViewModel::class.java]
    }
}