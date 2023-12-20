package com.example.myapplication.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

@Suppress("UNCHECKED_CAST")
object ViewModelProviderHelper {
    fun <T : ViewModel> createViewModel(owner: ViewModelStoreOwner, viewModel: T): T {
        return ViewModelProvider(
            owner = owner,
            factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return viewModel as T
                }
            })[viewModel::class.java]
    }
}