package com.android.fundamentals.workshop02

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.fundamentals.domain.login.LoginInteractor
import kotlinx.coroutines.Dispatchers

class Workshop2ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        Workshop2ViewModel::class.java -> Workshop2ViewModel(LoginInteractor(dispatcher = Dispatchers.Default))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}