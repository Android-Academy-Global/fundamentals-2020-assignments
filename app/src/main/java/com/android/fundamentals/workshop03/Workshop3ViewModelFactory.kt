package com.android.fundamentals.workshop03

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.fundamentals.workshop02_03.Workshop2Workshop3ViewModel

class Workshop3ViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        Workshop2Workshop3ViewModel::class.java -> {
            Workshop2Workshop3ViewModel(
                // TODO 16: Replace "TODO" stub with completed "Ws03LocationsRepository(applicationContext: Context)"
                repository = TODO()
            )
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}