package com.android.fundamentals.workshop02

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.fundamentals.PersistencyApp
import com.android.fundamentals.workshop02_03.Workshop2Workshop3ViewModel

class Workshop2ViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        Workshop2Workshop3ViewModel::class.java -> {
            val repository = (applicationContext as PersistencyApp).workshop2LocationRepository
            Workshop2Workshop3ViewModel(repository = repository)
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}