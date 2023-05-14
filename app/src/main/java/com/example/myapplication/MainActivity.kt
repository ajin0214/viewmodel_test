package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainViewController(
            rootView = findViewById(R.id.cl_main),
            viewModelStoreOwner = this,
            viewLifecycleObserver = this
        ).init()

        ViewModelProvider(this).get(MainViewModel::class.java)
    }
}