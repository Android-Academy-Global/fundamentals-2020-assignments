package com.android.fundamentals.workshop01.solution

interface Workshop1SolutionView {

    fun setLoading(loading: Boolean)

    fun showUserNameError()

    fun showPasswordError()

    fun showSuccess()
}