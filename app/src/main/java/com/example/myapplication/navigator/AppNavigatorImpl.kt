package com.example.myapplication.navigator

import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import com.example.myapplication.ui.DetailFragment
import com.example.myapplication.ui.MainFragment
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity):AppNavigator{

    override fun navigateTo(screen: Screens) {
        val fragment = when(screen){
            Screens.MAIN->DetailFragment()
            Screens.DETAIL->MainFragment()
        }

        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }
}