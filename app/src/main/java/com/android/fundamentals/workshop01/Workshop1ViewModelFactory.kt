package com.android.fundamentals.workshop01

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Workshop1ViewModelFactory(
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        Workshop1ViewModel::class.java -> {
            val sharedPreferences = TODO() // TODO 01: create correct link to sharedPreferences
            Workshop1ViewModel(sharedPreferences = sharedPreferences)
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}