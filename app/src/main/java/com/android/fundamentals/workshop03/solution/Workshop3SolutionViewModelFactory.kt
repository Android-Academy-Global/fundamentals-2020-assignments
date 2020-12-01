package com.android.fundamentals.workshop03.solution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.fundamentals.domain.LoginInteractor

class Workshop3SolutionViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        Workshop3SolutionViewModel::class.java -> Workshop3SolutionViewModel(LoginInteractor())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}