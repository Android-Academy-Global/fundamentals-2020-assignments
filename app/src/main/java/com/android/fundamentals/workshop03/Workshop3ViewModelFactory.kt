package com.android.fundamentals.workshop03

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.fundamentals.domain.location.LocationGenerator
import kotlinx.coroutines.Dispatchers

class Workshop3ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        Workshop3ViewModel::class.java -> Workshop3ViewModel(LocationGenerator(dispatcher = Dispatchers.Default))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}