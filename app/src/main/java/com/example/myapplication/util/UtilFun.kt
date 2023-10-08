package com.example.myapplication.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

class Utilfun {
}

fun hideKeyboard(activity: Activity) {
    val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = activity.currentFocus ?: View(activity)
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
