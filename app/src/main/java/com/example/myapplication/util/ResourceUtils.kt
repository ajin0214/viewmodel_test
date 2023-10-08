package com.example.myapplication.util

import androidx.annotation.StringRes

object ResourceUtils {
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return ApplicationContextProvider.getContext().getString(resId, formatArgs)

    }
}